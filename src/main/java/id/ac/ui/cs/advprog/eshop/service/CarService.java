package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import java.util.List;

public interface CarService extends BaseService<Car>{
    @Override
    Car create(Car car);
    @Override
    List<Car> findAll();
    @Override
    Car findById(String carId);
    @Override
    Car update(String carId, Car updatedCar);
    @Override
    void deleteById(String carId);
}
