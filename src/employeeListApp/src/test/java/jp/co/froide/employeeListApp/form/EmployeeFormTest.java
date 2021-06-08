package jp.co.froide.employeeListApp.form;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class EmployeeFormTest {

    @Autowired
    Validator validator;

    private EmployeeForm testEmployeeForm = new EmployeeForm();
    private BindingResult bindingResult = new BindException(testEmployeeForm, "employeeForm");

    @ParameterizedTest
    @ValueSource(strings = {"", "萩原　稔", "hagiharaminori", "HAGIHARA　MINORI"})
    public void name_test(String name) {
        testEmployeeForm.setName(name);
        validator.validate(testEmployeeForm, bindingResult);
        assertNull(bindingResult.getFieldError());
    }

//    @ParameterizedTest
//    @ValueSource(strings = {"ハギハラ　ミノリ","ハギハラミノリ","ﾊｷﾞﾊﾗﾞ　ﾐﾉﾘﾞ","はぎはら　みのり"})
//    public void furigana_test(String furigana){
//        testEmployeeForm.setFurigana(furigana);
//        validator.validate(testEmployeeForm,bindingResult);
//        assertNull(bindingResult.getFieldError());
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"12345678901","00000000000","677889"})
//    public void phone_number_test(String phone_number){
//        testEmployeeForm.setPhone_number(phone_number);
//        validator.validate(testEmployeeForm,bindingResult);
//        assertNull(bindingResult.getFieldError());
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"akjsd@bdakjs.sandj",""})
//    public void email_test(String email){
//        testEmployeeForm.setEmail(email);
//        validator.validate(testEmployeeForm,bindingResult);
//        assertNull(bindingResult.getFieldError());
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {""})
//    public void joining_date_test(String joining_date){
//        testEmployeeForm.setEmail(joining_date);
//        validator.validate(testEmployeeForm,bindingResult);
//        assertNull(bindingResult.getFieldError());
//    }
//
}
