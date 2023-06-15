package com.easyshop.admin.user.exporter;

import java.io.IOException;
import java.util.List;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.easyshop.common.entity.User;

import jakarta.servlet.http.HttpServletResponse;

public class UserCsvExporter extends AbstractExporter {

	public void export(List<User> listUsers, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "text/csv", "users_", ".csv");

		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

		String[] csvHeader = { "User ID", "E-mail", "First name", "Last name", "Roles", "Enabled" };
		String[] fieldMapping = { "id", "email", "firstName", "lastName", "roles", "enabled" };

		csvWriter.writeHeader(csvHeader);

		for (User user : listUsers) {
			csvWriter.write(user, fieldMapping);
		}

		csvWriter.close();
	}
}
