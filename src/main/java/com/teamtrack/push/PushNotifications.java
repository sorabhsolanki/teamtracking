/**
 * 
 */
package com.teamtrack.push;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.ApnsServiceBuilder;
import com.teamtrack.dto.PushNotificationDetails;

/**
 * @author parveenkumar
 * 
 */
public class PushNotifications {

	public void sendPush(PushNotificationDetails push) {
		System.out.println("Sending an iOS push notification...\nobj: " + push);
		ApnsServiceBuilder serviceBuilder = APNS.newService();
		String certPath = PushNotifications.class.getResource("").getPath();
		
		System.out.println(certPath + "../../../" + push.getCertificatePath());
		
		serviceBuilder.withCert(certPath + "../../../" + push.getCertificatePath(),
				push.getCertificatePassword()).withSandboxDestination();

		ApnsService service = serviceBuilder.build();

		// Payload with custom fields
		String payload = APNS.newPayload().alertBody(push.getMessage()).alertTitle(push.getAlertTitle())
				.sound("default").customField(push.getCustomField(), push.getCustomFieldValue()).build();

		System.out.println("payload: " + payload);
		service.push(push.getToken(), payload);

		System.out.println("The message has been hopefully sent...");
	}

	public static void tester(String[] args) {
		System.out.println("Sending an iOS push notification...");

		String token = "";
		String type = "dev";
		String message = "the test push notification message";

		try {
			token = args[0];
		} catch (Exception e) {
			System.out
					.println("Usage: PushNotifications devicetoken [message] [type prod|dev]");
			System.out
					.println("example: PushNotifications 1testdevicetoken3eb414627e78991ac5a615b4a2a95454c6ba5d18930ac137 'hi there!' prod");
			return;
		}
		try {
			message = args[1];
		} catch (Exception e) {
			System.out.println("no message defined, using '" + message + "'");
		}
		try {
			type = args[2];
		} catch (Exception e) {
			System.out.println("no API type defined, using " + type);
		}

		System.out.println("The target token is " + token);

		ApnsServiceBuilder serviceBuilder = APNS.newService();

		if (type.equals("prod")) {
			System.out.println("using prod API");
			String certPath = PushNotifications.class.getResource(
					"prod_cert.p12").getPath();
			serviceBuilder.withCert(certPath, "password")
					.withProductionDestination();
		} else if (type.equals("dev")) {
			System.out.println("using dev API");
			String certPath = PushNotifications.class.getResource(
					"dev_cert.p12").getPath();
			serviceBuilder.withCert(certPath, "password")
					.withSandboxDestination();
		} else {
			System.out.println("unknown API type " + type);
			return;
		}

		ApnsService service = serviceBuilder.build();

		// Payload with custom fields
		String payload = APNS.newPayload().alertBody(message)
				.alertTitle("test alert title").sound("default")
				.customField("custom", "custom value").build();

		// //Payload with custom fields
		// String payload = APNS.newPayload()
		// .alertBody(message).build();

		// //String payload example:
		// String payload =
		// "{\"aps\":{\"alert\":{\"title\":\"My Title 1\",\"body\":\"My message 1\",\"category\":\"Personal\"}}}";

		System.out.println("payload: " + payload);
		service.push(token, payload);

		System.out.println("The message has been hopefully sent...");
	}
}
