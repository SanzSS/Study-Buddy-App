package swipe;

import account_creation.Account;
import data.persistency.ChatDataAccess;
import data.persistency.ChatDataAccessInterface;
import data.persistency.ChatDatabase;
import swipe.screen.SwiperPresenterFormatter;
import data.persistency.UserDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SwiperInteractorTest {
    SwiperPresenter testPresenter;
    SwiperInputBoundary testInteractor;
    Account curr;
    Account potential;
    UserDatabase database;
    SwiperRequestModel testRequestT;
    SwiperRequestModel testRequestT2;
    SwiperRequestModel testRequestF;
    List<Object> chatRoomList;
    ChatDatabase chatDatabase;
    ChatDataAccess chatDataAccess;
    ChatDataAccessInterface chatDataAccessInterface;


    @BeforeEach
    void setUp() {
        chatRoomList = new ArrayList<>();
        chatDatabase = new ChatDatabase(chatRoomList);
        chatDataAccess = new ChatDataAccess();
        chatDataAccess.setChatdata(chatDatabase);
        chatDataAccessInterface = chatDataAccess;
        testPresenter = new SwiperPresenterFormatter();
        testInteractor = new SwiperInteractor(testPresenter, chatDataAccess);
        curr = new Account("Sanzhar", "password");
        potential = new Account("Potential", "pass");
        database = UserDatabase.getUserDatabase();
        UserDatabase.setCurrentUser(curr);
        UserDatabase.getAccounts().put("Sanzhar", curr);
        UserDatabase.getAccounts().put("Potential", potential);
        testRequestT = new SwiperRequestModel(true, potential);
        chatDataAccess = new ChatDataAccess();
        testRequestT2 = new SwiperRequestModel(true, curr);
        testRequestF = new SwiperRequestModel(false, curr);







    }
    @AfterEach
    void tearDown() {
        testPresenter = null;
        testInteractor = null;
        curr =null;
        potential = null;
        database = null;
        UserDatabase.setCurrentUser(null);
        testRequestT = null;
        testRequestT2 = null;
        testRequestF = null;
        UserDatabase.getAccounts().remove("Sanzhar");
        UserDatabase.getAccounts().remove("Potential");
        chatRoomList = null;
        chatDatabase = null;
        chatDataAccess.setChatdata(null);
        chatDataAccessInterface = null;
    }


    @Test
    void testCreate() {

        SwiperResponseModel result = testInteractor.create(testRequestT);
        assertTrue(curr.getMatches().contains(potential));
        assertFalse(potential.getMatches().contains(curr));
        assertEquals("N", result.getAccepted());
        UserDatabase.getUserDatabase().setCurrentUser(potential);
        SwiperResponseModel result2 = testInteractor.create(testRequestT2);
        assertEquals("Y", result2.getAccepted());
        assertTrue(curr.getMatches().contains(potential));
        assertTrue(potential.getMatches().contains(curr));

    }

    @Test
    void testCreate2(){
        SwiperResponseModel result = testInteractor.create(testRequestF);
        assertFalse(curr.getMatches().contains(potential));
        assertFalse(potential.getMatches().contains(curr));
        assertEquals("N", result.getAccepted());




    }

}