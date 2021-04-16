package io.ootb.schoolinfrontend.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.ootb.schoolinfrontend.core.LocalDateTimeJDeserializer;
import io.ootb.schoolinfrontend.core.LocalDateTimeJSerializer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@MappedSuperclass
@Data
@Slf4j
public abstract class AbstractBaseEntity implements Identifiable {

	@Column(name = "created_date")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	@JsonDeserialize(using = LocalDateTimeJDeserializer.class)
	@JsonSerialize(using = LocalDateTimeJSerializer.class)
	private LocalDateTime createdDate;

	@Column(name = "updated_date")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	@JsonDeserialize(using = LocalDateTimeJDeserializer.class)
	@JsonSerialize(using = LocalDateTimeJSerializer.class)
	private LocalDateTime updatedDate;

	@Column(name = "updated_by", nullable = true, length = 36)
	private String updatedBy;

	@Column(name = "created_by", nullable = true, length = 36)
	private String createdBy;

	@PrePersist
	public void onCreate() {
		createdDate = LocalDateTime.now();
		updatedDate = LocalDateTime.now();

		try {
			if (SecurityUtils.getSecurityManager() == null) {
				return;
			}

			User user = (User) SecurityUtils.getSubject().getPrincipal();
			if (user != null) {
				this.setCreatedBy(user.getId());
				this.setUpdatedBy(user.getId());
			}

		} catch (UnavailableSecurityManagerException ex) {
			log.debug("Shiro security manager was unavailable during entity creation. " + this.getClass() + " with Id="
					+ this.getId(), ex);
		}
	}

	@PreUpdate
	public void onUpdate() {
		updatedDate = LocalDateTime.now();

		try {
			if (SecurityUtils.getSecurityManager() == null) {
				return;
			}

			User user = (User) SecurityUtils.getSubject().getPrincipal();
			if (user != null) {
				this.setUpdatedBy(user.getId());
			}
		} catch (UnavailableSecurityManagerException ex) {
			log.debug("Shiro security manager was unavailable during entity update. " + this.getClass() + " with Id="
					+ this.getId(), ex);
		}
	}
}
