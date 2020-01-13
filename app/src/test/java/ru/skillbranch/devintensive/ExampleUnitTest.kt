package ru.skillbranch.devintensive

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun test_name() {
        println(Utils.parseFullName(null)) //null null
        println(Utils.parseFullName("")) //null null
        println(Utils.parseFullName(" ")) //null null
        println(Utils.parseFullName("John")) //John null
    }

    @Test
    fun test_date() {
        println(Date().format()) //14:00:00 27.06.19
        println(Date().format("HH:mm")) //14:00

        println(Date().add(2, TimeUnits.SECOND)) //Thu Jun 27 14:00:02 GST 2019
        println(Date().add(-4, TimeUnits.DAY)) //Thu Jun 23 14:00:00 GST 2019
    }

    @Test
    fun test_initial() {
        println(Utils.toInitials("john", "doe")) //JD
        println(Utils.toInitials("John", null)) //J
        println(Utils.toInitials(null, null)) //null
        println(Utils.toInitials(" ", "")) //null
    }

    @Test
    fun test_new_name() {
        println(Utils.transliteration("Женя Стереотипов")) //Zhenya Stereotipov
        println(Utils.transliteration("Amazing Петр", "_")) //Amazing_Petr
        println(Utils.transliteration("[mi mi m]i", " ")) //Amazing_Petr

    }

    @Test
    fun test_humanize() {
        println(Date().add(-2, TimeUnits.HOUR).humanizeDiff()) //2 часа назад
        println(Date().add(-5, TimeUnits.DAY).humanizeDiff()) //5 дней назад
        println(Date().add(2, TimeUnits.MINUTE).humanizeDiff()) //через 2 минуты
        println(Date().add(7, TimeUnits.DAY).humanizeDiff()) //через 7 дней
        println(Date().add(-400, TimeUnits.DAY).humanizeDiff()) //более года назад
        println(Date().add(500, TimeUnits.DAY).humanizeDiff()) //более чем через год
        println(Date().add(-2, TimeUnits.DAY).humanizeDiff()) //через 7 дней
        println(Date().add(-12, TimeUnits.DAY).humanizeDiff()) //более года назад
        println(Date().add(-101, TimeUnits.DAY).humanizeDiff()) //более чем через год
    }

    @Test
    fun test_userView() {
        val user = User.makeUser("Иванов Иван")
        user.lastVisit = Date().add(-4, TimeUnits.DAY)
        val userView = user.toUserView()
        userView.printMe()
    }

    @Test
    fun test_plural() {
        println(TimeUnits.SECOND.plural(1)) //1 секунду
        println(TimeUnits.MINUTE.plural(4)) //4 минуты
        println(TimeUnits.HOUR.plural(19)) //19 часов
        println(TimeUnits.DAY.plural(222)) //222 дня
    }

    @Test
    fun test_builder() {
        println(User.Builder().id("15")
            .firstName("first")
            .lastName("last")
            .avatar(null)
            .rating(12)
            .respect(15)
            .lastVisit(null)
            .isOnline(true)
            .build())
    }

    @Test
    fun test_truncate() {
        println("Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate()) //Bender Bending R...
        println("Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate(15)) //Bender Bending...
        println("A     ".truncate(3)) //A
    }

    @Test
    fun test_stripHTML(){
        println("<p class=\"title\">Образовательное IT-сообщество Skill Branch</p>".stripHtml()) //Образовательное IT-сообщество Skill Branch
        println("<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml()) //Образовательное IT-сообщество Skill Branch
    }
}
