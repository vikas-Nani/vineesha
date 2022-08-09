package com.vikas.projects.Wicket_application_spring_2;

import java.io.IOException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class Simplepage extends WebPage {
	
	private static final long serialVersionUID = 1L;
	@SpringBean
	private ServiceClass ser;

	public Simplepage(Long id) throws IOException, InterruptedException {

		
		//Save ob=new Save();

		add(new Link<Save>("Hellolink") {

			private static final long serialVersionUID = 1L;

			public void onClick() {
				
				setResponsePage(new Save(id));
			}

	

		});
		
		DataView<Employee> view1 = new DataView<Employee>("view1",new ListDataProvider<Employee>(ser.getallEmployeesoforg(id))){

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<Employee> item) {
				final Employee n = item.getModelObject();
				item.add(new Label("e1", n.getEmpid()));
				item.add(new Label("e2", n.getEmp_name()));
				item.add(new Label("e3", n.getEmp_team()));
				item.add(new Label("e4", n.getEmp_role()));
				item.add(new Link<UpdateClass>("updatelink"){
					
					private static final long serialVersionUID = 1L;

						public void onClick()
					     {
		
								setResponsePage(new UpdateClass(n.getEmpid(),id));
							
					     }
					});
					item.add(new Link<>("deletelink"){
					
					private static final long serialVersionUID = 1L;

						public void onClick()
					     {
		                   //ser.updateorg(id,n.getEmpid());
							ser.deleteemployeeorg(id,n.getEmpid());
		                   try {
							setResponsePage(new Simplepage(id));
						} catch (IOException | InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		                  
		                   		}
					});

			}

		};
		add(view1);
		add(new Link<>("homepage"){
			
			private static final long serialVersionUID = 1L;

				public void onClick()
			     {
                   
					try {
						setResponsePage(new HomePage(new PageParameters()));
					} catch (IOException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			     }
			});
		

	}

	

}
