package com.vikas.projects.Wicket_application_spring_2;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

import java.io.IOException;

import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private ServiceClass ser;

	public HomePage(final PageParameters pageParameters) throws IOException, InterruptedException {
		super(pageParameters);

		add(new Link<NewOrganization>("org") {

			private static final long serialVersionUID = 1L;

			public void onClick() {

				setResponsePage(new NewOrganization());

			}
		});

		DataView<Organization> view = new DataView<Organization>("view",
				new ListDataProvider<Organization>(ser.getallOrganizations())) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<Organization> item) {
				final Organization n = item.getModelObject();
				item.add(new Label("c1", n.getOid()));
				item.add(new Label("c2", n.getOrg_name()));
				item.add(new Label("c3", n.getOrg_type()));
				item.add(new Label("c4", n.getOrg_location()));
				item.add(new Link<Simplepage>("mylink") {

					private static final long serialVersionUID = 1L;

					public void onClick() {

						try {
							setResponsePage(new Simplepage(n.getOid()));
						} catch (IOException | InterruptedException e) {
							e.printStackTrace();
						}
					}
				});
				item.add(new Link<Simplepage>("urlink") {

					private static final long serialVersionUID = 1L;

					public void onClick() {

						ser.deleteemployee(n.getOid());

						try {
							setResponsePage(new HomePage(new PageParameters()));
						} catch (IOException | InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				item.add(new Link<Simplepage>("theirlink") {

					private static final long serialVersionUID = 1L;

					public void onClick() {

						setResponsePage(new Updateorg(n.getOid()));
					}
				});

			}
		};
		add(view);

	}

}
