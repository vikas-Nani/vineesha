package com.vikas.projects.Wicket_application_spring_2;



import java.io.IOException;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class Save extends WebPage{
	
	private static final long serialVersionUID = 1L;


	@SpringBean
	ServiceClass ser;
	

public String name="abc";
public String team="dev";
public String role="team member";
	public Save(Long id) {
		add(new FeedbackPanel("feedback"));
		Form<?> nan=new Form<>("form"){
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit()
			{
			 
			 String status=ser.addemployee(id, new Employee(id,name,team,role));
			  super.onSubmit();
			  if(status.equals("success")) {
				  info("Employee saved successfully");
			  }
			  else {
				  info(status);
			  }
			}
			 
		};
		PropertyModel<String> model1=new PropertyModel<>(this,"name");
		TextField<String> id1=new TextField<>("s1",model1);
		nan.add(id1);
		PropertyModel<String> model2=new PropertyModel<>(this,"team");
		TextField<String> id2=new TextField<>("s2",model2);
		nan.add(id2);
		PropertyModel<String> model3=new PropertyModel<>(this,"role");
		TextField<String> id3=new TextField<>("s3",model3);
		nan.add(id3);
		
		
		System.out.println(name);
		
		nan.add(new Link<HomePage>("home") {

			private static final long serialVersionUID = 1L;

			public void onClick() {
				
				try {
					setResponsePage(new HomePage(new PageParameters()));
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		add(nan);
		
	}
	

}
