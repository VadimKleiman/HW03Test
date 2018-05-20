package ru.spbau.mit.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TableElement {
    public WebElement element;


    public TableElement(WebElement element) {
        this.element = element;
    }

    public int getRowsCount() {
        List<WebElement> tableRows = element.findElements(By.tagName("tr"));
        return tableRows.size();
    }

    public WebElement getElement() {
        return element;
    }

    public void setElement(WebElement element) {
        this.element = element;
    }

    public List<String> getUserNames() {
        return getColumnByID(1);
    }

    public List<String> getFullName() {
        return getColumnByID(2);
    }

    public List<String> getEmailAndJabber() {
        return getColumnByID(3);
    }

    public List<String> getGroups() {
        return getColumnByID(4);
    }

    public List<String> getLastAccess() {
        return getColumnByID(5);
    }

    public boolean removeAt(int index) {
        try {
            List<WebElement> tableRows = element.findElements(By.tagName("tr"));
            WebElement row = tableRows.get(index);
            row.findElements(By.className("block")).get(0).click();
        } catch (Exception ignore) {
            return false;
        }
        return true;
    }

    private List<String> getColumnByID(int id) {
        List<WebElement> tableRows = element.findElements(By.xpath("tr//td[" + String.valueOf(id) + "]"));
        List<String> out = new ArrayList<String>();
        for (WebElement row : tableRows) {
            out.add(row.getText());
        }
        return out;
    }
}
