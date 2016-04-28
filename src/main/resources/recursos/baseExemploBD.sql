/* 
 * O presente arquivo tem como objetivo criar alguns exemplos de Usuários 
 * e livros iniciais para o Banco de Dados. 
 * A execução do mesmo é portanto NÃO OBRIGATÓRIA.
 */
/**
 * Author:  Natarajan 
 * Created: 29/02/2016
 */


-- inserção de usuários exemplo

INSERT INTO Usuario (email, senha, foto, nomeCompleto, apelido, dataNascimento, cidade, estado, eh_admin) 
VALUES ('jose@gmail.com', 'jose', 'img/profiles/jose.jpg', 'José Ferreira', 'josé', '18/10/1990', 'São João do Rio do Peixe', 'pb', 'true');

INSERT INTO Usuario (email, senha, foto, nomeCompleto, apelido, dataNascimento, cidade, estado, eh_admin) 
VALUES ('natarajan@gmail.com', 'natara', 'img/profiles/natarajan2.jpg', 'Natarajan Rodrigues', 'natarajan', '28/03/1984', 'Sousa', 'pb', 'true');

INSERT INTO Usuario (email, senha, foto, nomeCompleto, apelido, dataNascimento, cidade, estado, eh_admin) 
VALUES ('ellida@gmail.com', 'ellida', 'img/profiles/reader-default.png', 'Éllida Kaline Calixto', 'ellida', '21/06/1991', 'Sousa', 'pb', 'false');


-- inserção de livros
INSERT INTO Livro(isbn, capa, titulo, ano, editora, autores, area_tema) 
VALUES ('111', 'img/livros/ManifestoDoPartidoComunista.jpg', 'Manifesto do Partido Comunista', '2000', 'L&PM Pocket', 'Marx & Engels', 'Economia Política');

INSERT INTO Livro(isbn, capa, titulo, ano, editora, autores, area_tema) 
VALUES ('222', 'img/livros/cemanos.jpg', 'Cem anos de solidão', '2001', 'D. Quixote', 'Gabriel García Marquez', 'Literatura Estrangeira');

INSERT INTO Livro(isbn, capa, titulo, ano, editora, autores, area_tema) 
VALUES ('333', 'img/livros/ervadiabo.jpeg', 'A erva do diabo', '2002', 'Best Seller', 'Carlos Castañeda', 'Literatura Estrangeira / Antropologia');

INSERT INTO Livro(isbn, capa, titulo, ano, editora, autores, area_tema) 
VALUES ('444', 'img/livros/mulheres.jpg', 'Mulheres', '2003', 'L&PM', 'Charles Bukowski', 'Literatura Estrangeira');

