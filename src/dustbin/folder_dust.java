package dustbin;

import label.Element;

public class folder_dust {
    public String father_folder_name;
    public int folder_number;

    public Element element;

    public folder_dust(String father_folder_name, int folder_number, Element element) {
        this.father_folder_name = father_folder_name;
        this.folder_number = folder_number;
        this.element = element;
    }

    public String getFather_folder_name() {
        return father_folder_name;
    }

    public void setFather_folder_name(String father_folder_name) {
        this.father_folder_name = father_folder_name;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public int getFoldernumber() {
        return folder_number;
    }

    public void setFoldernumber(int foldernumber) {
        this.folder_number = folder_number;
    }
}
