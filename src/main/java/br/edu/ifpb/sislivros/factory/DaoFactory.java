/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.factory;

/**
 *
 * @author José
 */
public class DaoFactory {
    
    public static final int DAO_BD = 0;

    public static DaoFactoryIF createFactory(int factoryType) {
        if (factoryType == DAO_BD){
            return new DaoFactoryDB();
        }
        return null;
    }
}
