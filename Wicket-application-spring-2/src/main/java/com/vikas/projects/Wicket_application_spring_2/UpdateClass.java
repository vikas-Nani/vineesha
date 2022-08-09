package com.vikas.projects.Wicket_application_spring_2;

import java.io.IOException;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class UpdateClass extends WebPage {
	
	private static final long serialVersionUID = 1L;
	@SpringBean
	private ServiceClass ser;
	public String name;
	public String team;
	public String role;

	
	public UpdateClass(Long empid,Long id) {
		// TODO Auto-generated constructor stub
		add(new FeedbackPanel("feedback1"));
		Form<?> nan=new Form<>("form1"){
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit()
			{
			 
			 ser.updateemployee(empid, new Employee(1l,name,team,role));
			 System.out.println("hello"+" "+name+" "+team+" "+role);
			 try {
				setResponsePage(new Simplepage(id));
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  super.onSubmit();
			}
			 
		};
		PropertyModel<String> model1=new PropertyModel<>(this,"name");
		TextField<String> id1=new TextField<>("z1",model1);
		nan.add(id1);
		PropertyModel<String> model2=new PropertyModel<>(this,"team");
		TextField<String> id2=new TextField<>("z2",model2);
		nan.add(id2);
		PropertyModel<String> model3=new PropertyModel<>(this,"role");
		TextField<String> id3=new TextField<>("z3",model3);
		nan.add(id3);
		
		
		
		nan.add(new Link<HomePage>("employee") {

			private static final long serialVersionUID = 1L;

			public void onClick() {
				
				try {
					setResponsePage(new Simplepage(id));
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		add(nan);
		DataView<Employee> view1 = new DataView<Employee>("de",new ListDataProvider<Employee>(ser.getemployeebyid(empid))){

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<Employee> item) {
				final Employee n = item.getModelObject();
				name=n.getEmp_name();
				role=n.getEmp_role();
				team=n.getEmp_team();
				item.add(new Label("f1", n.getEmpid()));
				item.add(new Label("f2", n.getEmp_name()));
				item.add(new Label("f3", n.getEmp_team()));
				item.add(new Label("f4", n.getEmp_role()));
			}
		};
		add(view1);
		
	}
}
