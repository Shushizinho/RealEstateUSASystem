module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires org.apache.commons.lang3;
    requires AuthLib;
    requires commons.math;
    requires javafx.swing;
    requires javafx.web;


    opens pt.ipp.isep.dei.esoft.project.ui to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.application.controller to javafx.fxml;
    exports pt.ipp.isep.dei.esoft.project.ui ;
    exports pt.ipp.isep.dei.esoft.project.ui.console;
    exports pt.ipp.isep.dei.esoft.project.domain;
    exports pt.ipp.isep.dei.esoft.project.ui.gui;
    exports pt.ipp.isep.dei.esoft.project.application.controller;
    opens pt.ipp.isep.dei.esoft.project.ui.gui to javafx.fxml;
    exports pt.ipp.isep.dei.esoft.project.service;
}