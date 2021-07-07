/**
 * Copyright (C), 2015-2021
 * FileName: FileUpload
 * Author:   呵呵哒
 * Date:     2021/3/28 10:34
 * Description:
 */
package org.springblade.gps.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @创建人 hyp
 * @创建时间 2021/3/28
 * @描述
 */
@Data
@ApiModel(value = "FileUploadShow", description = "FileUploadShow")
public class FileUploadShow {

	@ApiModelProperty(value = "id")
	private String id;

	@ApiModelProperty(value = "attachcode")
	private String attachcode;

	@ApiModelProperty(value = "fileName")
	private String fileName;

	@ApiModelProperty(value = "fileSaveName")
	private String fileSaveName;

	@ApiModelProperty(value = "Floder")
	private String Floder;


}
