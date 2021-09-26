<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/components/taglib.jsp" %>

<style>
.error{
	color: red;
	
}
</style>
	<!-- breadcrumb-section -->
	<div class="breadcrumb-section breadcrumb-bg">
		<div class="container">
			<div class="row">	
				<div class="col-lg-8 offset-lg-2 text-center">
					<div class="breadcrumb-text">
						<h1>Liên hệ</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end breadcrumb section -->

	<!-- contact form -->
	<div class="contact-from-section mt-150 mb-150">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 mb-5 mb-lg-0">
					<div class="form-title">
						<h2>Hãy gửi góp ý của bạn !</h2>
					</div>
				 	<div id="form_status"></div>
					<div class="contact-form">
						<!--<form type="POST" id="fruitkha-contact" onSubmit="return valid_datas( this );">-->
						<form method="post">
						<p class="error">${msg}</p>
							<p>
								<input type="tel" placeholder="Số điện thoại" name="contact_phone" id="phone">		
								<input type="text" placeholder="Tiêu đề" name="contact_subject" id="subject">
							</p>
							<p style="display: flex;justify-content: space-between;"><form:errors path="c.contact_phone" cssClass="error"></form:errors>
							<form:errors path="c.contact_subject" cssClass="error"></form:errors></p>
							<p><textarea name="contact_message" id="message" cols="30" rows="10" placeholder="Nội dung ..."></textarea></p>
							<form:errors path="c.contact_message" cssClass="error"></form:errors>
							<p><input type="submit" value="Gửi thông tin"></p>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end contact form -->

	<!-- find our location -->
	<div class="find-location blue-bg">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<p> <i class="fas fa-map-marker-alt"></i> Find Our Location</p>
				</div>
			</div>
		</div>
	</div>
	<!-- end find our location -->

	<!-- google map section -->
	<div class="embed-responsive embed-responsive-21by9">
		<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d26432.42324808999!2d-118.34398767954286!3d34.09378509738966!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x80c2bf07045279bf%3A0xf67a9a6797bdfae4!2sHollywood%2C%20Los%20Angeles%2C%20CA%2C%20USA!5e0!3m2!1sen!2sbd!4v1576846473265!5m2!1sen!2sbd" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" class="embed-responsive-item"></iframe>
	</div>
	<!-- end google map section -->
