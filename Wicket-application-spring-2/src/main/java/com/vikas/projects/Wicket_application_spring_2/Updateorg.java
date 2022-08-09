package com.vikas.projects.Wicket_application_spring_2;

import java.io.IOException;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class Updateorg extends WebPage{
	
	private static final long serialVersionUID = 1L;

	@SpringBean
	ServiceClass ser;
	
	String oname;
	String otype;
	String location;
	public Updateorg(Long id){
		Form<?> form=new Form<>("keep") {
			protected void onSubmit() {
				ser.updateorgdetails(id, new Organization(id,oname,otype,location));
				info("Upadte Success");
				try {
					setResponsePage(new HomePage(new PageParameters()));
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		form.add(new TextField<String>("oname",new PropertyModel<String>(this, "oname")));
		form.add(new TextField<String>("otype",new PropertyModel<String>(this, "otype")));
		form.add(new TextField<String>("location",new PropertyModel<String>(this, "location")));
		form.add(new Link<>("c") {
			public void onClick() {
				try {
					setResponsePage(new HomePage(new PageParameters()));
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		add(form);
		DataView<Organization> view1 = new DataView<Organization>("ed",new ListDataProvider<Organization>(ser.getorgbyid(id))){

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<Organization> item) {
				final Organization n = item.getModelObject();
				oname=n.getOrg_name();
				otype=n.getOrg_type();
				location=n.getOrg_location();
				item.add(new Label("g1", n.getOid()));
				item.add(new Label("g2", n.getOrg_name()));
				item.add(new Label("g3", n.getOrg_type()));
				item.add(new Label("g4", n.getOrg_location()));
			}
		};
		add(view1);
		
		
	}

}
