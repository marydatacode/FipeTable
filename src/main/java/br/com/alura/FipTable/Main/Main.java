package br.com.alura.FipTable.Main;

import br.com.alura.FipTable.Model.Models;
import br.com.alura.FipTable.Model.Data;
import br.com.alura.FipTable.Model.Vehicle;
import br.com.alura.FipTable.Service.ConsumptionApi;
import br.com.alura.FipTable.Service.ConvertData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner reading = new Scanner(System.in);
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumptionApi consumption = new ConsumptionApi();
    private ConvertData convert = new ConvertData();

    public void showMenu() {
        var menu = """
                *** OPTIONS ***
                Car
                Motorcycle
                Truck
                
                Enter one of the options to consult:
                """;

        System.out.println(menu);
        var option = reading.nextLine();
        String address;

        if (option.toLowerCase().contains("car"))  {
            address = URL_BASE + "carros/marcas";
        } else if (option.toLowerCase().contains("moto")) {
            address = URL_BASE + "motos/marcas";
        } else {
            address = URL_BASE + "caminhoes/marcas";
        }

        var json = consumption.getData(address);
        System.out.println(json);
        var brands = convert.getList(json, Data.class);
        brands.stream()
                .sorted(Comparator.comparing(Data::code))
                .forEach(System.out::println);

        System.out.println("Inform the code of the brand for consult");
        var codeBrand = reading.nextLine();

        address = address + "/"+  codeBrand + "/modelos";
        json = consumption.getData(address);
        var modelsList = convert.getData(json, Models.class);

        System.out.println("\nModels from this brand: ");
        modelsList.models().stream()
                .sorted(Comparator.comparing(Data::code))
                .forEach(System.out::println);

        System.out.println("\nEnter a part of the name of the car to be searched");
        var nameVehicles = reading.nextLine();

        List<Data> filterModels = modelsList.models().stream()
                .filter(m -> m.name().toLowerCase().contains(nameVehicles.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nFilters Models: ");
        filterModels.forEach(System.out::println);

        System.out.println("Enter the code from the model to search the value of rating");
        var codeModel = reading.nextLine();

        address = address + "/" + codeModel + "/anos";
        json =  consumption.getData(address);
        List<Data> years = convert.getList(json, Data.class);
        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i <  years.size();i++) {
                var addressYears  = address +  "/" + years.get(i).code();
                json = consumption.getData(addressYears);
                Vehicle vehicle = convert.getData(json, Vehicle.class);
                vehicles.add(vehicle);
        }

        System.out.println("\nAll vehicles filtered with reviews by years: ");
        vehicles.forEach(System.out::println);


    }
}
