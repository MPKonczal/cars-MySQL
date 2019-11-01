package pl.edu.utp.wtie.homeworkweek7.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.utp.wtie.homeworkweek7.dao.CarDao;
import pl.edu.utp.wtie.homeworkweek7.model.Car;
import pl.edu.utp.wtie.homeworkweek7.model.enumeration.Color;
import pl.edu.utp.wtie.homeworkweek7.model.enumeration.Mark;

@Route("car-gui")
public class CarGui extends VerticalLayout {

    private CarDao carDao;

    @Autowired
    public CarGui(CarDao carDao) {
        this.carDao = carDao;

        Grid<Car> carGrid = initCarGrid();
        addNewCar(carGrid);
        searchCarsByProductionYears(carGrid);
        showAllCars(carGrid);

        add(carGrid);
    }


    private Grid<Car> initCarGrid() {
        Grid<Car> carGrid = new Grid<>(Car.class);
        carGrid.setColumns("carId", "mark", "model", "color", "productionYear");
        carGrid.setItems(carDao.findAllCars());

        return carGrid;
    }


    private void addNewCar(Grid<Car> carGrid) {
        FormLayout newCarLayout = new FormLayout();

        ComboBox<Mark> markComboBox = new ComboBox<>("Mark");
        markComboBox.setItems(Mark.values());

        ComboBox<String> modelComboBox = new ComboBox<>("Model");
        modelComboBox.setItems("");
        markComboBox.addValueChangeListener(event -> {
            if (markComboBox.getValue() != null) {
                switch (markComboBox.getValue()) {
                    case ALFA_ROMEO:
                        modelComboBox.setItems("4C", "Giulia", "Giulietta", "Mito");
                        break;
                    case FERRARI:
                        modelComboBox.setItems("458", "488 GTB", "California", "F12 Berlinetta", "FF", "LaFerrari");
                        break;
                    case HONDA:
                        modelComboBox.setItems("Accord", "CR-V", "CR-Z", "Civic", "HR-V", "Jazz", "NSX");
                        break;
                    case MCLAREN:
                        modelComboBox.setItems("570S Spider", "650S", "MP4-12C", "P1", "Senna");
                        break;
                    case MERCEDES_BENZ:
                        modelComboBox.setItems("AMG GT", "Actros", "Citan", "Klasa CLA", "Klasa CLS", "GLC", "Vito");
                        break;
                    case RENAULT:
                        modelComboBox.setItems("Captur", "Clio", "Fluence", "Kadjar", "Kangoo", "Megane", "Scenic");
                        break;
                    case TOYOTA:
                        modelComboBox.setItems("Auris", "Avensis", "Aygo", "Corolla", "GT 86", "RAV4", "Yaris");
                        break;
                }
            } else {
                modelComboBox.setItems("");
            }
        });

        ComboBox<Color> colorComboBox = new ComboBox<>("Color");
        colorComboBox.setItems(Color.values());

        ComboBox<Integer> productionYearComboBox = new ComboBox<>("Production Year");
        productionYearComboBox.setItems(2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019);

        Dialog carDialog = new Dialog();
        carDialog.setWidth("300px");
        carDialog.setHeight("50px");

        Button newCarButton = new Button("Add car", new Icon(VaadinIcon.CAR));
        newCarButton.setIconAfterText(true);
        newCarButton.addClickListener(clickEvent -> {
            carDialog.removeAll();
            if (markComboBox.getValue() == null || modelComboBox.getValue() == null ||
                    modelComboBox.getValue().equals("") || colorComboBox.getValue() == null ||
                    productionYearComboBox.getValue() == null) {
                carDialog.add(new Label("Enter all car details!"));
            } else {
                carDao.saveCar(markComboBox.getValue(), modelComboBox.getValue(),
                        colorComboBox.getValue(), productionYearComboBox.getValue());
                carDialog.add(new Label("Adding a new car completed successfully!"));
                carGrid.setItems(carDao.findAllCars());
            }
            carDialog.open();
        });

        newCarLayout.add(markComboBox, modelComboBox, colorComboBox, productionYearComboBox, newCarButton);
        newCarLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("20em", 1),
                new FormLayout.ResponsiveStep("25em", 2),
                new FormLayout.ResponsiveStep("30em", 3),
                new FormLayout.ResponsiveStep("35em", 4),
                new FormLayout.ResponsiveStep("40em", 5));

        add(newCarLayout);
    }


    private void searchCarsByProductionYears(Grid<Car> carGrid) {
        FormLayout foundCarsLayout = new FormLayout();

        NumberField fromNumberField = new NumberField();
        fromNumberField.setPlaceholder("From");

        NumberField toNumberField = new NumberField();
        toNumberField.setPlaceholder("To");

        Button foundCarsButton = new Button("Show cars by production years");
        foundCarsButton.addClickListener(clickEvent -> {
            if (fromNumberField.getValue() != null && toNumberField.getValue() != null) {
                carGrid.setItems(carDao.getCarsBetweenYearsOfProduction(fromNumberField.getValue().intValue(),
                        toNumberField.getValue().intValue()));
            }
        });

        foundCarsLayout.add(fromNumberField, toNumberField, foundCarsButton);
        foundCarsLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("40em", 1),
                new FormLayout.ResponsiveStep("40em", 2),
                new FormLayout.ResponsiveStep("40em", 3));

        setHorizontalComponentAlignment(Alignment.CENTER, foundCarsLayout);
        add(foundCarsLayout);
    }


    private void showAllCars(Grid<Car> carGrid) {
        FormLayout allCarsLayout = new FormLayout();

        Button allCarsButton = new Button("Show all cars");
        allCarsButton.addClickListener(clickEvent -> carGrid.setItems(carDao.findAllCars()));

        allCarsLayout.add(allCarsButton);
        allCarsLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("20em", 1));

        setHorizontalComponentAlignment(Alignment.CENTER, allCarsLayout);
        add(allCarsLayout);
    }
}
