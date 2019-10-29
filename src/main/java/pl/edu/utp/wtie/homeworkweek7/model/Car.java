package pl.edu.utp.wtie.homeworkweek7.model;

import pl.edu.utp.wtie.homeworkweek7.model.enumeration.Color;
import pl.edu.utp.wtie.homeworkweek7.model.enumeration.Mark;

public class Car {

    private long carId;
    private Mark mark;
    private String model;
    private Color color;
    private int productionYear;

    public Car(long carId, Mark mark, String model, Color color, int productionYear) {
        this.carId = carId;
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.productionYear = productionYear;
    }

    public Car() {
    }


    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }


    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public long getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", productionYear=" + productionYear +
                '}';
    }
}
