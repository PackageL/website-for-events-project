package com.sda;


import com.sda.model.Event;
import com.sda.model.Role;
import com.sda.model.User;
import com.sda.repository.EventRepository;
import com.sda.repository.RoleRepository;
import com.sda.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class ServiceTesting {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EventRepository eventRepository;


    @Test
    @Order(1)
    public void testSaveUser()  {
        User user = new User();

        user.setUsername("user1234@com");
        user.setPassword("1234");

        User savedUser = userRepository.save(user);
        User existingUser = entityManager.find(User.class, savedUser.getId());


        Assertions.assertEquals(user.getUsername(), existingUser.getUsername());
        Assertions.assertEquals(user.getPassword(),existingUser.getPassword());
    }
    @Test
    @Order(2)
    public void testFindUsersByUsername(){

        User user = userRepository.findUserByUsername("user1234@com");

        User existingUser = entityManager.find(User.class, user.getId());

        Assertions.assertEquals(user.getUsername(),existingUser.getUsername());
    }
    @Test
    @Order(3)
    public void testFindAllUsers() {
        List<User> users = userRepository.findAll();
        assertEquals(2, users.size());
    }

    @Test
    @Order(4)
    public void testCreateRole(){
        Role bigBob = new Role();
        bigBob.setName("BigBob");
        bigBob.setActive(true);

        Role savedRole = roleRepository.save(bigBob);
        Role existingRole = entityManager.find(Role.class,savedRole.getId());

        Assertions.assertEquals(savedRole.getId(),existingRole.getId());
    }

    @Test
    @Order(5)
    public void testFindRoleByName(){
        Optional<Role> role = roleRepository.findByName("BigBob");

        Role existingRole = entityManager.find(Role.class,role.get().getId());
        assertEquals(role.get().getName(), existingRole.getName());
    }

    @Test
    @Order(6)
    public void testUpdateRole() {
        Role updateRole = roleRepository.findRoleById(1L);
        updateRole.setName("BOOOM!");
        Role savedRole = roleRepository.save(updateRole);
        Role existingRole = entityManager.find(Role.class, savedRole.getId());
        Assertions.assertEquals(savedRole.getId(),existingRole.getId());
    }

    @Test
    @Order(7)
    public void testFindAllRoles() {
        List<Role> roles = roleRepository.findAll();
        assertEquals(4,roles.size());
    }

    @Test
    @Order(8)
    public void testDeleteRoleById(){

        Role findRole = roleRepository.findRoleById(1L);
        findRole.setActive(false);

        Role savedRole = roleRepository.save(findRole);
        Role existingRole = entityManager.find(Role.class,savedRole.getId());
        Assertions.assertEquals(savedRole.getId(),existingRole.getId());

    }
    @Test
    @Order(9)
    public void testRestoreRoleById(){

        Role findRole = roleRepository.findRoleById(1L);
        findRole.setActive(true);

        Role savedRole = roleRepository.save(findRole);
        Role existingRole = entityManager.find(Role.class,savedRole.getId());
        Assertions.assertEquals(savedRole.getId(),existingRole.getId());

    }

    @Test
    @Order(10)
    public void testFindRoleById(){
        Optional<Role> role = roleRepository.findById(1L);
        assertEquals(role.get().getId(), 1L);
    }

    @Test
    @Order(11)
    public void testCreateEvent(){
        Event event = new Event();

        event.setTitle("Geek Squad");
        event.setDescription("Get your best nerd costume on ," +
                "There will be a competition for the best outfit, so get your geek on ;-) " +
                "Bring your own booze, but food is free, 20 euro entry fee or give the gawk gawk 2000 to big Bob");
        event.setStartDate(LocalDate.of(2023 , 2 ,10));
        event.setEndDate(LocalDate.of(2023,2,10));


        Event savedEvent = eventRepository.save(event);
        Event existingEvent = entityManager.find(Event.class, savedEvent.getId());


        Assertions.assertEquals(event.getTitle(), existingEvent.getTitle());
        Assertions.assertEquals(event.getDescription(),existingEvent.getDescription());
        Assertions.assertEquals(event.getStartDate(),existingEvent.getStartDate());
        Assertions.assertEquals(event.getEndDate(),existingEvent.getEndDate());
    }

    @Test
    @Order(12)
    public void testGetAllEvents(){
        List<Event> events = eventRepository.findAll();
        assertEquals(2,events.size());
    }

    @Test
    @Order(13)
    public void testFindEventById(){
        Optional<Event> event = eventRepository.findById(5L);
        assertEquals(event.get().getId(), 5L);
    }

}







