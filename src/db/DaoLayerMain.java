package db;

import dao.AbobnentDaoImpl;
import dao.LocationDaoImpl;
import entity.Abonent;
import entity.Location;
import exception.DaoException;

import java.util.List;
import java.util.Optional;

public class DaoLayerMain {
    public static void main(String[] args) throws DaoException {
//        List<Abonent> abonenets = AbobnentDaoImpl.getInstance().findAll();
//        System.out.println(abonenets);

        System.out.println("__________________________");

        //DaoLayerMain.deleteAbonenet(10L);

        //DaoLayerMain.updateAbonent();

       //saveAbonenet();

        //List<Abonent> abonenets = AbobnentDaoImpl.getInstance().findAll();
        //System.out.println(abonenets);

        //List<Abonent> abonents = AbobnentDaoImpl.getInstance().findByName("Piter");

        // System.out.println(abonents);

        System.out.println("Tests of LocationDao");

        locationFindAll();
        locationDaoFindById();
        locationDaoSave();
        locationDaoUpdate();
        locationDaoDelete();


    }

    private static void deleteAbonenet(Long id) throws DaoException {
        AbobnentDaoImpl abobnentDao = AbobnentDaoImpl.getInstance();

        System.out.println("Abonent was deleted : " + abobnentDao.delete(id));

    }

    private static void  updateAbonent() throws DaoException {
        AbobnentDaoImpl abonentDao = AbobnentDaoImpl.getInstance();
        Optional<Abonent> abonentForUpdate = abonentDao.findById(1L);
        System.out.println(abonentForUpdate);
        abonentForUpdate.ifPresent(abonent->{
            abonent.setName("Solomon");
            try {
                abonentDao.update(abonent);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void saveAbonenet(){
        AbobnentDaoImpl abonentDao = AbobnentDaoImpl.getInstance();
        Abonent abonent = new Abonent();
        abonent.setName("Piter");
        abonent.setPhone(1234567);
        try {
            Abonent savedAbonent = abonentDao.save(abonent);
            System.out.println(savedAbonent);

        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

    }

    private static void locationFindAll(){
        LocationDaoImpl locationDao = LocationDaoImpl.getInstance();
        try {
            System.out.println(locationDao.findAll());
        } catch (DaoException e) {
            new RuntimeException(e);
        }
    }

    private static void locationDaoFindById(){
        LocationDaoImpl locationDao = LocationDaoImpl.getInstance();
        try {
            System.out.println(locationDao.findById(2L));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    private static  void locationDaoUpdate(){
        LocationDaoImpl locationDao = LocationDaoImpl.getInstance();
        try {
            locationDao.update(new Location(2L, "Adler"));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    private static void locationDaoDelete(){
        LocationDaoImpl locationDao = LocationDaoImpl.getInstance();
        try {
            System.out.println(locationDao.delete(5L));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    private static void locationDaoSave(){
        LocationDaoImpl locationDao = LocationDaoImpl.getInstance();
        try {
            System.out.println(locationDao.save(new Location("Berlin")));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
