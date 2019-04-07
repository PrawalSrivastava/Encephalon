package com.thelogicals.modal;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Topic.class)
public abstract class Topic_ {

	public static volatile SingularAttribute<Topic, Topic> parentTopic;
	public static volatile SingularAttribute<Topic, Date> lastUpdated;
	public static volatile SingularAttribute<Topic, Integer> nodeLevel;
	public static volatile SingularAttribute<Topic, String> parentTagString;
	public static volatile SingularAttribute<Topic, String> topicName;
	public static volatile SingularAttribute<Topic, Long> id;

}

