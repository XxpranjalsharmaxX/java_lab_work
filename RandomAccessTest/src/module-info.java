package randomAccess;
 2  
 3  import java.io.*;
 4  import java.time.*;
 5  
 6  /**
 7  * @version 1.14 2018-05-01
 8  * @author Cay Horstmann
 9  */
10  public class RandomAccessTest
11  {
12    public static void main(String[] args) throws IOException
13    {
14        var staff = new Employee[3];
15    
16        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
17        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
18        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);
19    
20        try (var out = new DataOutputStream(new FileOutputStream("employee.dat")))
21        {
22          // save all employee records to the file employee.dat
23          for (Employee e : staff)
24              writeData(out, e);
25        }
26    
27        try (var in = new RandomAccessFile("employee.dat", "r"))
28        {
29          // retrieve all records into a new array
30    
31          // compute the array size
32          int n = (int)(in.length() / Employee.RECORD_SIZE);
33          var newStaff = new Employee[n];
34          
35          // read employees in reverse order
36          for (int i = n - 1; i >= 0; i--)
37          {
38              newStaff[i] = new Employee();
39              in.seek(i * Employee.RECORD_SIZE);
40              newStaff[i] = readData(in);
41          }
42          
43          // print the newly read employee records
44          for (Employee e : newStaff)
45              System.out.println(e);
46        }
47    }
48  
49      /**
50      * Writes employee data to a data output
51      * @param out the data output
52      * @param e the employee
53      */
54      public static void writeData(DataOutput out, Employee e) throws IOException
55      {
56        DataIO.writeFixedString(e.getName(), Employee.NAME_SIZE, out);
57        out.writeDouble(e.getSalary());
58        
59        LocalDate hireDay = e.getHireDay();
60        out.writeInt(hireDay.getYear());
61        out.writeInt(hireDay.getMonthValue());
62        out.writeInt(hireDay.getDayOfMonth());
63      }
64  
65      /**
66      * Reads employee data from a data input
67      * @param in the data input
68      * @return the employee
69      */
70      public static Employee readData(DataInput in) throws IOException
71      {
72        String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
73        double salary = in.readDouble();
74        int y = in.readInt();
75        int m = in.readInt();
76        int d = in.readInt();
77        return new Employee(name, salary, y, m - 1, d);
78      }
79  }