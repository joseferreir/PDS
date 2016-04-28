/*
--configurações gerais para criação do banco
CREATE DATABASE sislivros
  WITH ENCODING='UTF8'
       OWNER=postgres
       LC_COLLATE='pt_BR.UTF-8'
       LC_CTYPE='pt_BR.UTF-8'
       CONNECTION LIMIT=-1;
*/


/*
table usuario

Requistos básicos
Nome Completo, apelido, e-mail, data de nascimento, cidade, 
estado, foto, tipo (administrador ou não administrador);
*/
CREATE TABLE Usuario(
    id SERIAL NOT NULL, 
    foto VARCHAR(100),
    email VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(32) NOT NULL,
    nomeCompleto VARCHAR(100) NOT NULL,
    apelido VARCHAR(30),
    dataNascimento DATE NOT NULL,
    cidade VARCHAR(30) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    eh_admin BOOLEAN DEFAULT 'FALSE',
    PRIMARY KEY(id)

);


-- table amizade
CREATE TABLE Amizade(
    idSolicitante INTEGER NOT NULL,
    idSolicitado INTEGER NOT NULL,
    estah_aceito BOOLEAN DEFAULT 'FALSE',
    FOREIGN KEY(idSolicitante) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY(idSolicitado) REFERENCES Usuario(id)  ON DELETE CASCADE,
    PRIMARY KEY(idSolicitante, idSolicitado)
);


/*
table Livro
Requisitos básicos
Título, ano de publicação, editora, autores, foto da capa, tema/área, isbn;
*/
CREATE TABLE Livro (
    id SERIAL NOT NULL,
    isbn VARCHAR(30) UNIQUE NOT NULL,
    capa VARCHAR(200) NOT NULL,
    titulo VARCHAR(200) NOT NULL,
    area_tema VARCHAR(200),
    ano INTEGER NOT NULL,
    rating  DECIMAL(16,5) DEFAULT 0, 
    editora VARCHAR(120) NOT NULL,
    autores VARCHAR(120) NOT NULL,
    PRIMARY KEY(id)
);


/*
Table Avalicao
Requisitos básicos
A identificação única, os comentários e o rating (de 1 a 5);
*/
CREATE TABLE Avaliacao( /* prefiro não colocar id aqui  */
    id SERIAL NOT NULL,
    idUsuario INTEGER NOT NULL,
    idLivro INTEGER NOT NULL,
    rating INTEGER NOT NULL,
    comentario VARCHAR,
    FOREIGN KEY (idUsuario) REFERENCES Usuario (id) ON DELETE CASCADE,
    FOREIGN KEY (idLivro) REFERENCES  Livro(id) ON DELETE CASCADE, 
    PRIMARY KEY(idUsuario, idLivro)
);


-- table de recomendacao de livros
CREATE TABLE Recomendacao(
    id SERIAL, 
    idRemetente INTEGER NOT NULL,
    idDestinatario   INTEGER NOT NULL,
    idLivro INTEGER NOT NULL,
    dataRecomendacao TIMESTAMP NOT NULL,
    FOREIGN KEY (idRemetente) REFERENCES Usuario (id) ON DELETE CASCADE,
    FOREIGN KEY (idDestinatario) REFERENCES Usuario (id) ON DELETE CASCADE,
    FOREIGN KEY (idLivro) REFERENCES Livro (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);


/*
Table grupo
Requisitos básicos
A identificação única, o nome (único) e a descrição textual;
*/
CREATE TABLE Grupo(
    id SERIAL NOT NULL,
    --idUserDono INTEGER,
    nome VARCHAR(200) NOT NULL UNIQUE,
    descricao VARCHAR(1000),
    PRIMARY KEY(id)
);


-- table de participantes dos grupos
CREATE TABLE ParticipaGrupo (
    idUsuario INTEGER NOT NULL,
    idGrupo INTEGER NOT NULL,
    eh_Participante BOOLEAN DEFAULT 'TRUE',
    FOREIGN KEY (idUsuario) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (idGrupo) REFERENCES Grupo(id) ON DELETE CASCADE, 
    PRIMARY KEY (idUsuario, idGrupo)
);


-- table topicos dos grupos
CREATE TABLE Topico (
    id SERIAL NOT NULL,
    idGrupo INTEGER NOT NULL,
    idLivro INTEGER NOT NULL,
    descricao VARCHAR(2000) NOT NULL,
    FOREIGN KEY (idLivro) REFERENCES Livro (id) ON DELETE CASCADE,
    FOREIGN KEY (idGrupo) REFERENCES Grupo (id) ON DELETE CASCADE, 
    PRIMARY KEY (id)
);


-- table comentarios nos topicos
CREATE TABLE ComentarioTopico(
    id SERIAL NOT NULL,
    idUsuario INTEGER NOT NULL,
    idTopico INTEGER NOT NULL,
    comentario VARCHAR(5000) NOT NULL,
    dateTime TIMESTAMP NOT NULL, 
    FOREIGN KEY (idUsuario) REFERENCES Usuario (id) ON DELETE RESTRICT,
    FOREIGN KEY (idTopico) REFERENCES Topico (id) ON DELETE RESTRICT, 
    PRIMARY KEY(id)
);


-- Insere Usuario ADMIN padrão | user/senha: "admin@admin"/"admin"
INSERT INTO Usuario (email, senha, foto, nomeCompleto, apelido, dataNascimento, cidade, estado, eh_admin) 
VALUES ('admin@admin.com', 'admin', 'img/profiles/reader-default.png', 'Admin', 'admin', '18/10/1954', 'Santa Cruz', 'pb', 'true');
-- procedimento iserir o criador do crupo na tabela participaGrupo


-- created by José
-- CREATE OR REPLACE FUNCTION insetCriadorGrupo(INTEGER)
-- RETURNS void 
-- AS '
--      DECLARE
--      idusuario ALIAS FOR $1;
--                 idGrupo  INTEGER;
--       BEGIN
--                   SELECT INTO  idGrupo MAX(id) 
--                   FROM GRUPO;
--                   INSERT INTO participagrupo VALUES(idusuario,idgrupo,TRUE);
--                   
--        END '
-- LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION updateAvaliacaoLivro()
RETURNS TRIGGER
AS 
'
    DECLARE 
        media DECIMAL;
    BEGIN
        SELECT INTO media AVG (rating)
        FROM Avaliacao
        WHERE idLivro = NEW.idLivro;
        
        UPDATE Livro SET rating = media WHERE id = NEW.idLivro;
        RETURN NULL;
   END

'
LANGUAGE plpgsql;



CREATE TRIGGER ratingLivroTrigger 
AFTER INSERT OR UPDATE OR DELETE 
ON Avaliacao 
FOR EACH ROW
EXECUTE PROCEDURE updateAvaliacaoLivro();


