# REST API using Spring Boot JPA
<table>
  <tr>
    <th>Relationship between tables</th>
    <th>Annotation</th>
    <th>Java Code</th>
  </tr>
  <tr>
    <td>ONE address -- ONE student</td>
    <td>@OneToOne</td>
    <td>public class Address {<br>
    @OneToOne(mappedBy = "address")<br>
    @JsonIgnoreProperties("address")<br>
    private Student student;<br>}
    <br>--------------------------------------------------------------------<br>
    public class Student {<br>
    @OneToOne(cascade = CascadeType.ALL)<br>
    @JoinColumn(name = "address_id", referencedColumnName = "id")<br>
    @JsonIgnoreProperties("student")<br>
    private Address address;<br>}
    </td>
  </tr>
  <tr>
    <td>MANY students <-- ONE course</td>
    <td><p>@ManyToOne</p><p>@OneToMany</p></td>
    <td><p>public class Student {<br>
    @ManyToOne<br>
    @JoinColumn(name="course_id")<br>
    @JsonIgnoreProperties("students")<br>
    private Course course;<br>}
    <br>--------------------------------------------------------------------<br>
    @Table(name="course")<br>
    public class Course {<br>
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)<br>
    @JsonIgnoreProperties("course")<br>
    private Set&lt;Student&gt; students;<br>}
    </td>
  </tr>
  <tr>
    <td>MANY courses <--> MANY topics</td>
    <td>@ManyToMany</td>
    <td>
    @Table(name="topic")<br>
    public class Topic {<br>
    @ManyToMany(cascade = {CascadeType.ALL})<br>
    @JoinTable(<br>
    &emsp;&emsp;&emsp;&emsp;name = "Topic_Course",<br>
    &emsp;&emsp;&emsp;&emsp;joinColumns = {@JoinColumn(name = "topic_id")},<br>
    &emsp;&emsp;&emsp;&emsp;inverseJoinColumns = {@JoinColumn(name = "course_id")}<br>
    @JsonIgnoreProperties("topics")<br>
    private Set&lt;Course&gt; courses;<br>}
    <br>--------------------------------------------------------------------<br>
    @Table(name="course")<br>
    public class Course {<br>
    @ManyToMany(mappedBy = "courses")<br>)<br>
    @JsonIgnoreProperties("courses")<br>
    private Set&lt;Topic&gt; topics;<br>}
    </td>
  </tr>
</table>
