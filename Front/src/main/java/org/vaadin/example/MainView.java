package org.vaadin.example;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route
public class MainView extends VerticalLayout {
    List<Starship> starships = new ArrayList<>();
    API api = new API();


    public MainView(@Autowired GreetService service) {
        HorizontalLayout inputs = new HorizontalLayout();
        VerticalLayout results = new VerticalLayout();

        //Grid
        Grid<Starship> grid = new Grid<>(Starship.class, false);
        grid.addColumn(Starship::getName).setHeader("Nombre");
        grid.addColumn(Starship::getModel).setHeader("Modelo");
        grid.addColumn(Starship::getManufacturer).setHeader("Manufacture");
        grid.addColumn(Starship::getLength).setHeader("Tamaño");
        grid.addColumn(Starship::getCrew).setHeader("Crew");
        grid.addColumn(// Botón para eliminar un Spaceship como una columna del grid
                new NativeButtonRenderer<>("Remove item",
                        clickedItem -> {
                            for (int i=0;i<starships.size();i++){
                                if(starships.get(i).getName().equals(clickedItem.getName())){
                                    starships.remove(i); // Elimina el Spaceship actual

                                }
                            }grid.setItems(starships); // Actualiza el grid

                        }));

        //Input para poner el número de página
        TextField textField = new TextField("Page");
        textField.addClassName("bordered");

        //Botón para leer la lista de caracteres
        Button boton1 = new Button("Lee lista de caracteres", e -> {
            int page = Integer.parseInt(textField.getValue()); // Obtiene el valor del TextField (el nº de pag)

            if (page == 0) {// si la pag es igual a cero
                try {
                    page = 1;// el valor de la pag es 1
                    //añade los datos de la pag (que mete en el metodo para llamar a swapi) a la lista de starships
                    starships.addAll(api.getSWAPI(page));
                    List<Starship> newItems = api.getSWAPI(page);//obtiene los datos de la pag
                    ListDataProvider<Starship> dataProvider = (ListDataProvider<Starship>) grid.getDataProvider();
                    List<Starship> currentItems = new ArrayList<>(dataProvider.getItems());
                    currentItems.addAll(newItems);
                    grid.setItems(currentItems);
                } catch (Exception ex) {
                    System.out.println("Error al obtener datos: " + ex.getMessage());
                }
            } else if (page < 0 || page > 5) { // si la pag es menor que 0 o mayor que 5
                System.out.println("La página no es correcta");
                Notification.show("La página no es correcta");
            } else {
                try {
                    starships.addAll(api.getSWAPI(page));
                    List<Starship> newItems = api.getSWAPI(page);
                    ListDataProvider<Starship> dataProvider = (ListDataProvider<Starship>) grid.getDataProvider();
                    List<Starship> currentItems = new ArrayList<>(dataProvider.getItems());
                    currentItems.addAll(newItems);
                    grid.setItems(currentItems);
                } catch (Exception ex) {
                    System.out.println("Error al obtener datos: " + ex.getMessage());
                }
            }
        });


        Button boton2 = new Button("Guardar",
                e -> {
                    int page= Integer.parseInt(textField.getValue());
                    //System.out.println(Spaceship.to);
                    try {
                        API.postData("starship", api.getSWAPI(page) );//Llamo al méotodo post de la clase API
                    } catch (URISyntaxException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                });

        add(textField, boton1, grid, boton2);
    }

}
