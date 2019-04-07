package com.thelogicals.modal;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(QuestionMeta.class)
public abstract class QuestionMeta_ {

	public static volatile SingularAttribute<QuestionMeta, String> addedByUser;
	public static volatile SingularAttribute<QuestionMeta, Topic> topic;
	public static volatile SingularAttribute<QuestionMeta, Boolean> personalQuestion;
	public static volatile SingularAttribute<QuestionMeta, Long> id;

}

