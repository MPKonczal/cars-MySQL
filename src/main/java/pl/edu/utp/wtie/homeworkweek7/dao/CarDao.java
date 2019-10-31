package pl.edu.utp.wtie.homeworkweek7.dao;

import pl.edu.utp.wtie.homeworkweek7.model.Car;
import pl.edu.utp.wtie.homeworkweek7.model.enumeration.Color;
import pl.edu.utp.wtie.homeworkweek7.model.enumeration.Mark;

import java.util.List;

public interface CarDao {

    void saveCar(Mark mark, String model, Color color, int productionYear);

    List<Car> findAllCars();

    List<Car> getCarsBetweenYearsOfProduction(int yearFrom, int yearTo);
}
