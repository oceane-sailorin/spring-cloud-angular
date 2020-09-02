package com.sba.models.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * profile entity
 * 
 */
@Entity
@Getter
@Setter
@Table(name = "profiles")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ProfileEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id", nullable = false)
    private Integer id;

    @ApiModelProperty(notes = "Name of the profile", required = true)
    @NotNull(message = "{error.profile.naming.required}")
    @Column(name="profile_naming", nullable = false)
    private String naming;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "profiles_permissions", 
    joinColumns = @JoinColumn(name="profile_permission_profile", referencedColumnName = "profile_id"),
    inverseJoinColumns = @JoinColumn(name="profile_permission_permission", referencedColumnName = "permission_id"))
    private Set<PermissionEntity> permissions = new HashSet<>();  

    
   
    @Override
    public String toString() {
        return "ProfileEntity [id=" + id + ", naming=" + naming + "]";
    }
    
    public JsonNode getAll(ProfileEntity toMap)
    {
        JsonNode obj = null;
        ObjectMapper mapper = new ObjectMapper();
        obj = mapper.convertValue(toMap, JsonNode.class);
        /*try {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return obj;
    }

}
