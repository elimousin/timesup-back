import com.timesup.MainApplication
import com.timesup.controller.TimesUpController
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes =  [MainApplication::class])
class MainApplicationTest {

    @Autowired
    lateinit var timesUpController: TimesUpController

    @Test
    fun contextLoads() {
        Assertions.assertNotNull(timesUpController, "timesUpController should have been initialized")
    }

}