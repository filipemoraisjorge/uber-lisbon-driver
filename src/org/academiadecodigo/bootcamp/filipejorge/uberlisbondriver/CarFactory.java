package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver;


import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.Car;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.CarType;

public class CarFactory {

    public static Car getNewCar() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Car car = null;

        /**
         * Create a new car Randomly
         * based on CarType Enum
         */
        int differentCarTypes = CarType.values().length;
        int carTypeIndex = RandomF.getRandom(0, differentCarTypes - 1);


        Class carType = CarType.values()[carTypeIndex].getCarClass();
        String carTypeName = carType.getName();

        car = (Car) Class.forName(carTypeName).newInstance();

        return car;

    }

    public static Car getNewCarbyType(CarType type) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Car car = null;
        Class carType = type.getCarClass();
        String carTypeName = carType.getName();

        car = (Car) Class.forName(carTypeName).newInstance();

        return car;
    }
}
