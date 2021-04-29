package polis.uicomponents;

import javafx.scene.control.Slider;

/**
 *  Slider voor het beheren van de simulatie snelheid. Dit is een aparte klasse
 *  zodat alle waarden zeker juist geconfigureerd zijn tegenover de timeline.
 * **/

public class TimeSlider extends Slider {

    public TimeSlider(){
        this.setMin(25);
        this.setMax(300);
        this.setBlockIncrement(1);
        this.showTickLabelsProperty();
        this.setValue(100);
    }

}
