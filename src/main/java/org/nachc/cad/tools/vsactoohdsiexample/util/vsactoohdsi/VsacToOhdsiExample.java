package org.nachc.cad.tools.vsactoohdsiexample.util.vsactoohdsi;

import java.io.File;
import java.sql.Connection;

import org.apache.commons.lang.StringUtils;
import org.nachc.cad.tools.vsactoohdsi.tools.ExtractOhdsiConceptIdListFromVsacZip;
import org.nachc.cad.tools.vsactoohdsi.util.dvo.ohdsi.cdm.OhdsiConceptList;
import org.nachc.cad.tools.vsactoohdsi.util.parser.vsac.valueset.dvo.VsacValueSetRow;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VsacToOhdsiExample {

	public static void getConceptIdList(File file, Connection conn) {
		log.info("Getting connection...");
		log.info("Got file: \n" + FileUtil.getCanonicalPath(file));
		log.info("Extracting concepts...");
		OhdsiConceptList conceptList = ExtractOhdsiConceptIdListFromVsacZip.exec(file, conn);
		String listAsString = conceptList.getListAsString();
		String msg = "";
		msg += ("\nRequested: " + conceptList.getRequested());
		msg += ("\nFound:     " + conceptList.getFound());
		msg += ("\nNot Found: " + conceptList.getNotFoundCount());
		msg += "\n";
		log.info("Summary: " + msg);
		log.info("OHDSI Concept List: \n" + listAsString + "\n");
		log.info("Summary: " + msg);
		// log the missing list
		if (conceptList.getNotFoundCount() > 0) {
			msg = "";
			msg += "\n";
			msg += StringUtils.rightPad("CODE SYSTEM", 20);
			msg += StringUtils.rightPad("CODE", 10);
			msg += StringUtils.rightPad("OID", 35);
			msg += StringUtils.rightPad("DESCRIPTOR", 80);
			msg += "\n";
			msg += StringUtils.rightPad("-----------", 20);
			msg += StringUtils.rightPad("----", 10);
			msg += StringUtils.rightPad("---", 35);
			msg += StringUtils.rightPad("----------", 80);
			for (VsacValueSetRow row : conceptList.getNotFoundList()) {
				msg += "\n";
				msg += StringUtils.rightPad(row.getCodeSystemName(), 20);
				msg += StringUtils.rightPad(row.getCode(), 10);
				msg += StringUtils.rightPad(row.getOID(), 35);
				msg += StringUtils.rightPad(row.getDescriptor(), 80);
			}
			msg += "\n\n";
			log.info("\n\nNOT FOUND LIST (The following concepts were not found in your OHDSI CDM concept table):" + msg);
		}
		log.info("Done getting concepts.");
	}
}
