package transferencias.presentation.main;

import transferencias.Application;

import java.awt.event.ActionEvent;

public class Controller{
    Model model;
    View view;

    public Controller(View view, Model model) {
        this.model = model;
        this.view = view;
        view.setModel(model);
        view.setController(this);
    }

    public void actionPerformed(ActionEvent e) {
        model.commit();
    }
    public void show(){
        Application.window.setContentPane(view.getPanel());
    }
}
