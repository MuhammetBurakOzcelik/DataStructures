import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

public class mainTest {
    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        Date date = new Date();
        String time=dateFormat.format(date);

        Experiment arr1 = new Experiment( "Expr1",  dateFormat.format(date),true, 2,(float)0.1);
        Experiment arr2 = new Experiment("Expr2",  dateFormat.format(date),false,1,(float)0.25);
        Experiment arr3 = new Experiment( "Expr3",  dateFormat.format(date),true, 0,(float)1.0);
        Experiment arr4 = new Experiment( "Expr4",  dateFormat.format(date),false,0,(float)0.1);
        Experiment arr5 = new Experiment( "Expr5",  dateFormat.format(date),true, 0,(float)0.2);
        Experiment arr6 = new Experiment( "Expr6",  dateFormat.format(date),false,0,(float)0.3);
        Experiment arr7 = new Experiment( "Expr7",  dateFormat.format(date),false,2,(float)0.3);
        Experiment arr8 = new Experiment("Expr8",  dateFormat.format(date),true,2, (float)7.4);
        Experiment arr9 = new Experiment( "Expr9",  dateFormat.format(date),false,4,(float)0.5);
        Experiment arr10 = new Experiment( "Expr10",dateFormat.format(date),true, 3,(float)0.6);
        Experiment arr11 = new Experiment( "Expr11",dateFormat.format(date),false,2,(float)10.7);
        Experiment arr12 = new Experiment( "Expr12",dateFormat.format(date),false,3,(float)5.8);
        ExperimentList container = new ExperimentList();

        System.out.println("----------------------");
        System.out.println("Add new experiment:");
        System.out.println(arr1.toString());
        container.addExp(arr1);
        container.listAll();

        System.out.println("----------------------");
        System.out.println("Add new experiment:");
        System.out.println(arr2.toString());
        container.addExp(arr2);
        container.listAll();

        System.out.println("----------------------");
        System.out.println("Add new experiment:");
        System.out.println(arr3.toString());
        container.addExp(arr3);
        container.listAll();

        System.out.println("----------------------");
        System.out.println("Add new experiment:");
        System.out.println(arr4.toString());
        container.addExp(arr4);
        container.listAll();

        System.out.println("----------------------");
        System.out.println("Add new experiment:");
        System.out.println(arr5.toString());
        container.addExp(arr5);
        container.listAll();

        System.out.println("----------------------");
        System.out.println("Add new experiment:");
        System.out.println(arr6.toString());
        container.addExp(arr6);
        container.listAll();

        System.out.println("----------------------");
        System.out.println("Add new experiment:");
        System.out.println(arr7.toString());
        container.addExp(arr7);
        container.listAll();

        System.out.println("----------------------");
        System.out.println("Add new experiment:");
        System.out.println(arr8.toString());
        container.addExp(arr8);
        container.listAll();

        System.out.println("----------------------");
        System.out.println("Add new experiment:");
        System.out.println(arr9.toString());
        container.addExp(arr9);
        container.listAll();

        System.out.println("----------------------");
        System.out.println("Add new experiment:");
        System.out.println(arr10.toString());
        container.addExp(arr10);
        container.listAll();

        System.out.println("----------------------");
        System.out.println("Add new experiment:");
        System.out.println(arr11.toString());
        container.addExp(arr11);
        container.listAll();

        System.out.println("----------------------");
        System.out.println("Add new experiment:");
        System.out.println(arr12.toString());
        container.addExp(arr12);
        container.listAll();

        try{
//            System.out.println("----------------------");
//            System.out.println("getExp(2,-1) Result:");
//            Experiment e1 = container.getExp(2,-1);
//            System.out.println(e1.toString());


            /* ------ Get Experiment ------*/
            System.out.println("----------------------");
            System.out.println("getExp(2,3) Result:");
            Experiment e = container.getExp(2,3);
            System.out.println(e.toString());


            /* ------ Set Experiment ------*/
            System.out.println("----------------------");
            System.out.println("setExp(2,3), accuracy=1.0");
            e.setAccuracy((float) 1.0);
            container.setExp(2,3, e);
            e = container.getExp(2,3);
            System.out.println("----------------------");
            System.out.println("getExp Result:");
            e = container.getExp(2,3);
            System.out.println(e.toString());


            /* ------ Remove Experiment (Day - Index) ------*/
            System.out.println("----------------------");
            System.out.println("removeExp(0,0) Result:");
            container.removeExp(0, 0);
            container.listAll();


            /* ------ Remove Experiment (Day) ------*/
            System.out.println("----------------------");
            System.out.println("removeDay(1) Result:");
            container.removeDay(1);
            container.listAll();


            /* ------ Order Day (Day) ------ */
            System.out.println("----------------------");
            System.out.println("orderDay(2) Result:");
            container.orderDay(2);

            /* ------ ListExp (Day) ------ */
            System.out.println("----------------------");
            System.out.println("ListExp(2) Result:");
            container.listExp(2);

            /* ------ Order Experiment ------ */
            System.out.println("----------------------");
            System.out.println("orderExperiment Result:");
            ExperimentList orderedList = container.orderExperiments();
            Iterator itr =  orderedList.iterator();
            while(itr.hasNext()) {
                System.out.println(itr.next().toString());
            }
        }catch (Exception e){
            System.err.println(Arrays.toString(e.getStackTrace()) + e.toString() );
        }
    }
}
