/* 
    Реализуйте структуру телефонной книги с помощью HashMap.
    Программа также должна учитывать, что в во входной структуре будут повторяющиеся 
    имена с разными телефонами, их необходимо считать, как одного человека с 
    разными телефонами. 
    Вывод должен быть отсортирован по убыванию числа телефонов. 
*/

package Seminar_6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;


class PhoneBook {
    private static HashMap<String, ArrayList<Integer>> phoneBook = new HashMap<>();
    

    public void add(String name, Integer phoneNum) {
        Boolean notSave = false;
        /*Проверим есть ли такой ключ (Фамилия Имя Отчечтво) */
        if (phoneBook.get(name) == null) {
            phoneBook.put(name, new ArrayList<Integer>());
        }
        /*Добавим номер*/
        /*Проверим наличие номера*/
        for (int index = 0; index < phoneBook.get(name).size(); index++) {
            if (phoneBook.get(name).get(index) == phoneNum){
                notSave = true;
            }
        }
        if (notSave == false){
            phoneBook.get(name).add(phoneNum);
        }
    }

    public String find(String name) {
        String result = new String();
        for (Map.Entry<String, ArrayList<Integer>> entry : phoneBook.entrySet()) {
            if (entry.getKey().equals(name)){
                result = name +" " + "tel->" + entry.getValue();
            }            
        }
        return result;
    }

    public static LinkedHashMap<String, ArrayList<Integer>> getPhoneBook() {
        HashMap<String, Integer> phoneBookSize = new HashMap<>();
        LinkedHashMap<String, ArrayList<Integer>> bookSort = new LinkedHashMap<>();
        for (Map.Entry<String, ArrayList<Integer>> entry : phoneBook.entrySet()) {
            phoneBookSize.put(entry.getKey(),entry.getValue().size());
        }
        phoneBookSize = sortByValue(phoneBookSize);
        for (Map.Entry<String, Integer> entr : phoneBookSize.entrySet()) {
            for (Map.Entry<String, ArrayList<Integer>> entry : phoneBook.entrySet()) {
                if (entry.getKey() == entr.getKey()){
                    bookSort.put(entry.getKey(),entry.getValue());
                }
            }
        }
        return bookSort;
    }

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        LinkedList<Map.Entry<String, Integer> > list =
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1, 
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
         
        // put data from sorted list to hashmap 
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}

public class HM_task_01 {
    public static void main(String[] args) {
        String name1;
        String name2;
        String name3;
        String name4;        
        int phone1;
        int phone2;
        int phone3;
        int phone4;
        int phone5;

        name1 = "Ivanov";
        name2 = "Petrov";
        name3 = "Sidorov";
        name4 = "Ktototo";
        phone1 = 123456;
        phone2 = 654321;
        phone3 = 789012;
        phone4 = 865433;
        phone5 = 901257;
    
        PhoneBook myPhoneBook = new PhoneBook();
        myPhoneBook.add(name1, phone1);
        myPhoneBook.add(name1, phone2);
        myPhoneBook.add(name1, phone5);

        myPhoneBook.add(name2, phone4);
        myPhoneBook.add(name2, phone3);

        myPhoneBook.add(name3, phone3);

        myPhoneBook.add(name4, phone4);

        System.out.println(myPhoneBook.find(name2));
        System.out.println(PhoneBook.getPhoneBook());
    }
}
