package hiber.dao;

import hiber.model.Car;

import java.util.List;

public interface CarDao {
    void save(Car car);
    List<Car> findAll();
}