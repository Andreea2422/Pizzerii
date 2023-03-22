package pizzashop.repository;

import pizzashop.model.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MenuRepository {
    private static String filename = "data/menu.txt";
    private List<Order> listMenu;

    public MenuRepository(){
    }

    private void readMenu(){
        //ClassLoader classLoader = MenuRepository.class.getClassLoader();
        File file = new File(filename);
        this.listMenu= new ArrayList();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line=br.readLine())!=null){
                Order menuItem=getMenuItem(line);
                listMenu.add(menuItem);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Order getMenuItem(String line){
        Order item=null;
        if (line.equals("")) return null;
        StringTokenizer st=new StringTokenizer(line, ",");
        String name= st.nextToken();
        double price = Double.parseDouble(st.nextToken());
        item = new Order(name, 0, price);
        return item;
    }

    public List<Order> getMenu(){
        readMenu();//create a new menu for each table, on request
        return listMenu;
    }
}