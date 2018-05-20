package ru.spbau.mit.element;

import org.openqa.selenium.WebElement;

public class InputElement {
    private WebElement element;

    public InputElement(WebElement element) {
        this.element = element;
    }

    public void setValue(String value) {
        this.element.sendKeys(value);
    }

    public String getValue() {
        return this.element.getAttribute("value");
    }

    public WebElement getElement() {
        return element;
    }

    public void setElement(WebElement element) {
        this.element = element;
    }
}
