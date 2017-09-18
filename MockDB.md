### Q5 If it is possible to mock DB in JUnit test? Give a case to show how to do it?.
Answer - Yes, it is possible to mock DB in JUnit test.

The following technique can be used for that - 
We can mock DB using **Mockito** library and mocking database access/connection methods.

In case of RawQuery we can search for regex and return object according to that.

Example - 
Suppose we want to test our DAO class like this - 
```java
public class UserDAO{
 public void create(User user) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(insert into user values(?,?));
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
           
            pstmt.execute();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
        } finally {
            close(connection);
            close(pstmt);
            close(generatedKeys);
        }
    }
}
```

We can test this DAO class like this - 
```java
@RunWith(MockitoJUnitRunner.class)
public class TestUserDAO {

    @Mock
    DataSource datasource;
    @Mock
    Connection connection;
    @Mock
    PreparedStatement preparedStatement;

    public TestUserDAO() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws SQLException {
        when(datasource.getConnection()).thenReturn(connection);
        when(datasource.getConnection(anyString(), anyString())).thenReturn(connection);
        doNothing().when(connection).commit();
        when(connection.prepareStatement(anyString(), anyString())).thenReturn(preparedStatment);
        doNothing().when(preparedStatement).setString(anyString(), anyString());
        when(preparedStatement.execute()).thenReturn(Boolean.TRUE);
    }
}
```
