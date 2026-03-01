package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;



@Repository
public class CarRepository extends AbstractInMemoryRepository<Car>{

    @Override
    protected String getId(final Car car) {
        return car.getCarId();
    }

    @Override
    protected void setId(final Car car,final String id) {
        car.setCarId(id);
    }

    @Override
    protected void copyField(final Car existingCar, final Car updatedCar) {
        existingCar.setCarName(updatedCar.getCarName());
        existingCar.setCarColor(updatedCar.getCarColor());
        existingCar.setCarQuantity(updatedCar.getCarQuantity());
    }

}
