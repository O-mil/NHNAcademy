package com.nhnacamemy.student.init;

import com.nhnacamemy.student.Controller.StudentListController;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@HandlesTypes(value = {
        com.nhnacamemy.student.Controller.Command.class
})
public class WebAppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {

        ControllerFactory controllerFactory = new ControllerFactory();
        controllerFactory.init(c);

        ctx.setAttribute("controllerFactory", controllerFactory);
    }


}
