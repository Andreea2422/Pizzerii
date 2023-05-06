module pizzashop {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;

    opens pizzashop.model;
    exports pizzashop.model;
    opens pizzashop;
    exports pizzashop;
    opens pizzashop.controller;
    exports pizzashop.controller;
    opens pizzashop.service;
    exports pizzashop.service;
    opens pizzashop.repository;
    exports pizzashop.repository;
}