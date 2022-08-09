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

public class NewOrganization extends WebPage {
	
	
	private static final long serialVersionUID = 1L;
	@SpringBean
	ServiceClass ser;
	Long oid;
	String oname;
	String otype;
	String location;
	public NewOrganization() {
		add(new FeedbackPanel("feedback3"));
		Form<?> nan=new Form<>("form3"){
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit()
			{
			 
			 ser.addOrganization(new Organization(oid,oname,otype,location));
			 
			 try {
				setResponsePage(new HomePage(new PageParameters()));
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  super.onSubmit();
			}
			 
		};
		PropertyModel<Long> model0=new PropertyModel<>(this,"oname");
		TextField<Long> id0=new TextField<>("y0",model0);
		nan.add(id0);
		PropertyModel<String> model1=new PropertyModel<>(this,"oname");
		TextField<String> id1=new TextField<>("y1",model1);
		nan.add(id1);
		PropertyModel<String> model2=new PropertyModel<>(this,"otype");
		TextField<String> id2=new TextField<>("y2",model2);
		nan.add(id2);
		PropertyModel<String> model3=new PropertyModel<>(this,"location");
		TextField<String> id3=new TextField<>("y3",model3);
		nan.add(id3);
		
		
		nan.add(new Link<>("cancel") {

			private static final long serialVersionUID = 1L;

			public void onClick() {
				
				try {
					setResponsePage(new HomePage(new PageParameters()));
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		add(nan);
		
		
	}

}
