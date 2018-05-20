package ru.spbau.mit.element;

import org.openqa.selenium.WebElement;

public class CheckBoxElement {
    private WebElement element;

    public CheckBoxElement(WebElement element) {
        this.element = element;
    }

    public boolean isSelected() {
        return element.isSelected();
    }

    public void selected(boolean value) {
        if (isSelected() != value) {
            element.click();
        }
    }

    public WebElement getElement() {
        return element;
    }

    public void setElement(WebElement element) {
        this.element = element;
    }
}
