module com.mycompany.grupo.trie {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.grupo.trie to javafx.fxml;
    exports com.mycompany.grupo.trie;
}
