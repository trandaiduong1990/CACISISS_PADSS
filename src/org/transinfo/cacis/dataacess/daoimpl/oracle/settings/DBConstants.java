
package org.transinfo.cacis.dataacess.daoimpl.oracle.settings;


public class DBConstants{

    public static final String driverName = "com.mysql.jdbc.Driver";
    public static final String dbUrl = "jdbc:mysql://localhost/catsdao";
    public static final String dbUsername = "";
    public static final String dbPassword = "";

    // Table names
    public static final String usersTableName = "user";
    public static final String employeesTableName = "employee";
    public static final String rolesTableName = "role";
    public static final String userRolesTableName = "user_role";
    public static final String coursesTableName = "course";
    public static final String courseEventsTableName = "courseevent";
    public static final String sequences = "sequences";    
    public static final String departmentsTableName = "departments";

    //Users table field names
    public static final String u_userId = "userid";
    public static final String u_name = "name";
    public static final String u_password = "password";
    public static final String u_employeeId ="employeeid";


    //Employees table field names
    public static final String e_employeeId = "employeeid";
    public static final String e_name="name";
    public static final String e_managerId = "managerid";

    //roles table field names
    public static final String r_roleId = "roleid";
    public static final String r_name ="name";
    public static final String r_description =  "description";

    //user roles field names
    public static final String ur_userId = "userid";
    public static final String ur_roleId ="roleid";

    //Course table field names
    public static final String c_employeeId = "employeeid";
    public static final String c_courseId = "courseid";
    public static final String c_courseName = "coursename";
    public static final String c_organiser = "organiser";
    public static final String c_fromDate = "fromdate";
    public static final String c_toDate = "todate";
    public static final String c_fees = "fees";
    public static final String c_gstincluded = "gstincluded";
    public static final String c_justification = "justification";
    public static final String c_status = "status";

    //Course events
    public static final String ce_courseEventId = "courseeventid";
    public static final String ce_courseId = "courseid";
    public static final String ce_timestamp = "timestamp";
    public static final String ce_eventType = "eventtype";
    public static final String ce_eventBy = "eventby";
    public static final String ce_comment = "comment";

    //Sequence name
    public static final String seq_name = "name";
    public static final String last_value = "last_value";
    
     //Department
    public static final String d_departmentId = "department_id";
    public static final String d_managerInCharge = "manager_in_charge";


}