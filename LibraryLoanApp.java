package com.erikw.libraryloan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LibraryLoanApp extends Application {

    private BookDataAccessObject buildDAO() {
        return new DatabaseBookDataAccessObject();
    }

    private Library buildModel() {
        return new Library(buildDAO());
    }

    private Controller buildController(Stage stage) {
        return new Controller(buildModel(), stage);
    }

    @Override
    public void start(Stage stage) throws Exception {
    	// we will use MVC patterns
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui.fxml"));
        loader.setControllerFactory(t -> buildController(stage));

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);

        
        /*BookDataAccessObject bookDataAccessObject =
                new DatabaseBookDataAccessObject();

        bookDataAccessObject.setup();

        Library model = new Library(bookDataAccessObject);
        model.addNewBook("TestName1", "John Smith_Jack Smith_Jane Smith", 2010);
        model.addNewBook("TestName1", "John Smith_Jack Smith_Jane Smith", 2010);
        model.addNewBook("TestName1", "John Smith_Jack Smith_Jane Smith", 2010);
        model.addNewBook("TestName1", "John Smith_Jack Smith_Jane Smith", 2010);

        model.addNewBook("TestName2", "John Smith_Jane Smith", 2013);
        model.addNewBook("TestName2", "John Smith_Jane Smith", 2013);
        model.addNewBook("TestName2", "John Smith_Jane Smith", 2013);
        model.addNewBook("TestName2", "John Smith_Jane Smith", 2013);

        model.addNewBook("Name3", "Jane Smith", 2015);
        model.addNewBook("Name3", "Jane Smith", 2015);
        model.addNewBook("Name3", "Jane Smith", 2015);

        bookDataAccessObject.close();
        System.exit(0);*/
    }
}
