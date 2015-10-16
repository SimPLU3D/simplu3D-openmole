name := "simplu3D-openmole-plugin"

version := "1.0"

scalaVersion := "2.11.6"

osgiSettings

OsgiKeys.exportPackage := Seq("!scala.*,!java.*,"  +
"!COM.jrockit.reflect.*;!COM.newmonics.PercClassLoader.*;!bitronix.tm.*;!bsh.*;!com.apple.eawt.*;!com.apple.eio.*;!com.apple.mrj.*;!com.catalysoft.swing.unicode.*;!com.coyotegulch.jisp.*;!com.enterprisedt.net.ftp.*;!com.hexidec.ekit.*;!com.hp.hpl.jena.ontology.tidy.*;!com.hp.hpl.jena.query.*;!com.hp.hpl.jena.reasoner.dig.*;!com.ibm.ejs.jts.jta.*;!com.ibm.uvm.tools.*;!com.ibm.websphere.jtaextensions.*;!com.ibm.ws.Transaction.*;!com.ibm.wsdl.extensions.soap.*;!com.jcraft.jsch.*;!com.jgoodies.looks.plastic.*;!com.jgoodies.looks.plastic.theme.*;!com.jogamp.common.*;!com.jogamp.common.jvm.*;!com.jogamp.common.nio.*;!com.jogamp.common.os.*;!com.jogamp.common.type.*;!com.jogamp.common.util.*;!com.jogamp.common.util.awt.*;!com.jogamp.common.util.cache.*;!com.jogamp.common.util.locks.*;!com.jogamp.gluegen.runtime.*;!com.jogamp.openal.*;!com.lowagie.toolbox.*;!com.sun.image.codec.jpeg.*;!com.sun.imageio.plugins.common.*;!com.sun.imageio.spi.*;!com.sun.javadoc.*;!com.sun.jdi.*;!com.sun.jdi.connect.*;!com.sun.jdi.event.*;!com.sun.jdi.request.*;!com.sun.jdmk.comm.*;!com.sun.jimi.core.*;!com.sun.media.sound.*;!com.sun.medialib.codec.g3fax.*;!com.sun.medialib.codec.g4fax.*;!com.sun.medialib.codec.jiio.*;!com.sun.medialib.codec.jp2k.*;!com.sun.medialib.codec.jpeg.*;!com.sun.medialib.codec.png.*;!com.sun.medialib.mlib.*;!com.sun.mirror.apt.*;!com.sun.mirror.declaration.*;!com.sun.mirror.type.*;!com.sun.mirror.util.*;!com.sun.msv.datatype.*;!com.sun.msv.datatype.xsd.*;!com.sun.net.ssl.*;!com.sun.net.ssl.internal.ssl.*;!com.sun.opengl.cg.*;!com.sun.opengl.util.*;!com.sun.org.apache.xml.internal.serialize.*;!com.sun.tools.apt.*;!com.sun.tools.javac.*;!com.sun.tools.javadoc.*;!com.sun.tools.javah.*;!com.sun.tools.javah.oldjavah.*;!com.sun.xml.fastinfoset.sax.*;!com.sun.xml.fastinfoset.stax.*;!com.sun.xml.stream.writers.*;!com.thoughtworks.qdox.*;!com.toedter.calendar.*;!com.touchgraph.graphlayout.*;!com.touchgraph.graphlayout.interaction.*;!com.twelvemonkeys.imageio.*;!com.twelvemonkeys.imageio.color.*;!com.twelvemonkeys.imageio.spi.*;!com.twelvemonkeys.imageio.stream.*;!com.twelvemonkeys.imageio.util.*;!com.twelvemonkeys.lang.*;!com.twelvemonkeys.util.*;!com.twelvemonkeys.util.regex.*;!gnu.gcj.*;!groovyjarjarasm.asm.tree.analysis.*;!javax.accessibility.*;!javax.annotation.*;!javax.annotation.processing.*;!javax.crypto.*;!javax.crypto.interfaces.*;!javax.crypto.spec.*;!javax.ejb.*;!javax.imageio.*;!javax.imageio.event.*;!javax.imageio.metadata.*;!javax.imageio.plugins.jpeg.*;!javax.imageio.spi.*;!javax.imageio.stream.*;!javax.inject.*;!javax.jdo.*;!javax.jdo.spi.*;!javax.jmdns.*;!javax.jms.*;!javax.lang.model.element.*;!javax.mail.*;!javax.mail.internet.*;!javax.management.*;!javax.management.modelmbean.*;!javax.management.remote.*;!javax.management.remote.rmi.*;!javax.management.timer.*;!javax.naming.*;!javax.naming.directory.*;!javax.naming.event.*;!javax.naming.spi.*;!javax.net.*;!javax.net.ssl.*;!javax.print.*;!javax.print.attribute.*;!javax.print.attribute.standard.*;!javax.rmi.*;!javax.rmi.ssl.*;!javax.script.*;!javax.security.auth.*;!javax.security.auth.callback.*;!javax.security.auth.login.*;!javax.security.auth.x500.*;!javax.security.cert.*;!javax.security.jacc.*;!javax.servlet.jsp.*;!javax.sound.sampled.*;!javax.sql.*;!javax.sql.rowset.*;!javax.swing.*;!javax.swing.border.*;!javax.swing.colorchooser.*;!javax.swing.event.*;!javax.swing.filechooser.*;!javax.swing.plaf.*;!javax.swing.plaf.basic.*;!javax.swing.plaf.metal.*;!javax.swing.plaf.synth.*;!javax.swing.table.*;!javax.swing.text.*;!javax.swing.text.html.*;!javax.swing.text.html.parser.*;!javax.swing.text.rtf.*;!javax.swing.tree.*;!javax.swing.undo.*;!javax.tools.*;!javax.validation.*;!javax.validation.constraints.*;!javax.validation.groups.*;!javax.validation.metadata.*;!javax.wsdl.*;!javax.wsdl.extensions.*;!javax.wsdl.extensions.http.*;!javax.wsdl.extensions.mime.*;!javax.wsdl.extensions.soap.*;!javax.wsdl.factory.*;!javax.wsdl.xml.*;!javax.xml.rpc.*;!javax.xml.rpc.encoding.*;!javax.xml.rpc.handler.*;!javax.xml.rpc.handler.soap.*;!javax.xml.rpc.holders.*;!javax.xml.rpc.server.*;!javax.xml.rpc.soap.*;!javax.xml.soap.*;!javax.xml.transform.stax.*;!jline.*;!jogamp.common.os.*;!jogamp.common.util.locks.*;!jrockit.vm.*;!jxl.*;!jxl.biff.*;!jxl.format.*;!jxl.read.biff.*;!jxl.write.*;!jxl.write.biff.*;!kaffe.util.*;!magick.*;!mondrian.olap.*;!mondrian.spi.*;!net.geom.j3d.objects.*;!net.jcip.annotations.*;!net.sf.cglib.beans.*;!net.sf.cglib.core.*;!net.sf.cglib.proxy.*;!net.sf.cglib.reflect.*;!net.sf.cglib.transform.*;!net.sf.cglib.transform.impl.*;!net.sf.saxon.xpath.*;!net.sourceforge.barbecue.*;!net.sourceforge.barbecue.linear.code39.*;!net.sourceforge.barbecue.linear.ean.*;!net.sourceforge.barbecue.output.*;!netscape.ldap.*;!nu.xom.*;!oracle.xml.parser.*;!oracle.xml.parser.v2.*;!org.apache.bsf.*;!org.apache.bsf.util.*;!org.apache.commons.cli.*;!org.apache.commons.discovery.*;!org.apache.commons.discovery.resource.*;!org.apache.commons.discovery.resource.classes.*;!org.apache.commons.discovery.resource.names.*;!org.apache.commons.discovery.tools.*;!org.apache.commons.javaflow.*;!org.apache.commons.net.ftp.*;!org.apache.commons.net.ftp.parser.*;!org.apache.commons.net.pop3.*;!org.apache.commons.net.smtp.*;!org.apache.crimson.jaxp.*;!org.apache.env.*;!org.apache.harmony.luni.util.*;!org.apache.ivy.*;!org.apache.ivy.core.cache.*;!org.apache.ivy.core.event.*;!org.apache.ivy.core.event.download.*;!org.apache.ivy.core.event.resolve.*;!org.apache.ivy.core.module.descriptor.*;!org.apache.ivy.core.module.id.*;!org.apache.ivy.core.report.*;!org.apache.ivy.core.resolve.*;!org.apache.ivy.core.settings.*;!org.apache.ivy.plugins.matcher.*;!org.apache.ivy.plugins.resolver.*;!org.apache.ivy.util.*;!org.apache.jackrabbit.webdav.*;!org.apache.jackrabbit.webdav.client.methods.*;!org.apache.jackrabbit.webdav.property.*;!org.apache.jackrabbit.webdav.version.*;!org.apache.jackrabbit.webdav.xml.*;!org.apache.log.*;!org.apache.oro.text.regex.*;!org.apache.poi.hdgf.extractor.*;!org.apache.poi.hpbf.extractor.*;!org.apache.poi.hslf.extractor.*;!org.apache.poi.hsmf.*;!org.apache.poi.hsmf.datatypes.*;!org.apache.poi.hsmf.extractor.*;!org.apache.poi.hssf.record.formula.*;!org.apache.poi.hssf.record.formula.eval.*;!org.apache.poi.hssf.record.formula.udf.*;!org.apache.poi.hwpf.*;!org.apache.poi.hwpf.extractor.*;!org.apache.poi.sl.usermodel.*;!org.apache.torque.task.*;!org.apache.velocity.*;!org.apache.velocity.app.*;!org.apache.velocity.context.*;!org.apache.velocity.servlet.*;!org.apache.xerces.utils.regex.*;!org.apache.xml.resolver.*;!org.apache.xml.resolver.readers.*;!org.apache.xml.resolver.tools.*;!org.apache.xmlbeans.impl.xpath.saxon.*;!org.apache.xmlbeans.impl.xquery.saxon.*;!org.apache.xmlrpc.*;!org.codehaus.jettison.*;!org.codehaus.jettison.mapped.*;!org.codehaus.plexus.interpolation.*;!org.codehaus.plexus.interpolation.os.*;!org.codehaus.plexus.interpolation.reflection.*;!org.codehaus.plexus.interpolation.util.*;!org.daml.kazuki.*;!org.eclipse.core.internal.runtime.auth.*;!org.eclipse.core.resources.*;!org.eclipse.emf.validation.internal.*;!org.eclipse.emf.validation.model.*;!org.eclipse.emf.validation.service.*;!org.eclipse.jface.text.*;!org.eclipse.jface.text.rules.*;!org.eclipse.swt.graphics.*;!org.eclipse.swt.layout.*;!org.eclipse.swt.widgets.*;!org.eclipse.text.edits.*;!org.eclipse.ui.*;!org.freehep.graphics2d.*;!org.freehep.util.export.*;!org.fusesource.jansi.*;!org.gjt.xpp.*;!org.ietf.jgss.*;!org.jaxen.*;!org.jaxen.dom.*;!org.jaxen.dom4j.*;!org.jaxen.jdom.*;!org.jaxen.pattern.*;!org.jaxen.saxpath.*;!org.joda.convert.*;!org.jvnet.fastinfoset.*;!org.jvnet.staxex.*;!org.krysalis.barcode4j.*;!org.krysalis.barcode4j.impl.*;!org.krysalis.barcode4j.impl.codabar.*;!org.krysalis.barcode4j.impl.code128.*;!org.krysalis.barcode4j.impl.code39.*;!org.krysalis.barcode4j.impl.datamatrix.*;!org.krysalis.barcode4j.impl.fourstate.*;!org.krysalis.barcode4j.impl.int2of5.*;!org.krysalis.barcode4j.impl.pdf417.*;!org.krysalis.barcode4j.impl.postnet.*;!org.krysalis.barcode4j.impl.upcean.*;!org.krysalis.barcode4j.output.*;!org.krysalis.barcode4j.output.bitmap.*;!org.krysalis.barcode4j.output.svg.*;!org.krysalis.barcode4j.tools.*;!org.mockito.asm.signature.*;!org.objectweb.asm.tree.*;!org.objectweb.asm.tree.analysis.*;!org.objectweb.asm.util.*;!org.objectweb.jonas_tm.*;!org.objectweb.jotm.*;!org.omg.CORBA.*;!org.omg.CosNaming.*;!org.openxmlformats.schemas.officeDocument.x2006.math.*;!org.openxmlformats.schemas.schemaLibrary.x2006.main.*;!org.springframework.beans.factory.*;!org.springframework.beans.factory.xml.*;!org.springframework.core.io.*;!org.testng.*;!org.testng.annotations.*;!schemasMicrosoftComOfficePowerpoint.*;!schemasMicrosoftComOfficeWord.*;!sun.applet.*;!sun.awt.*;!sun.awt.image.*;!sun.awt.image.codec.*;!sun.io.*;!sun.java2d.pipe.*;!sun.misc.*;!sun.nio.cs.*;!sun.reflect.*;!sun.rmi.rmic.*;!sun.rmi.server.*;!sun.security.action.*;!sun.security.provider.*;!sun.tools.javac.*;!sun.tools.native2ascii.*;!weblogic.*;!weblogic.apache.xml.serialize.*,*")

OsgiKeys.importPackage := Seq("!scala.*,!java.*,"  +
"!COM.jrockit.reflect.*;!COM.newmonics.PercClassLoader.*;!bitronix.tm.*;!bsh.*;!com.apple.eawt.*;!com.apple.eio.*;!com.apple.mrj.*;!com.catalysoft.swing.unicode.*;!com.coyotegulch.jisp.*;!com.enterprisedt.net.ftp.*;!com.hexidec.ekit.*;!com.hp.hpl.jena.ontology.tidy.*;!com.hp.hpl.jena.query.*;!com.hp.hpl.jena.reasoner.dig.*;!com.ibm.ejs.jts.jta.*;!com.ibm.uvm.tools.*;!com.ibm.websphere.jtaextensions.*;!com.ibm.ws.Transaction.*;!com.ibm.wsdl.extensions.soap.*;!com.jcraft.jsch.*;!com.jgoodies.looks.plastic.*;!com.jgoodies.looks.plastic.theme.*;!com.jogamp.common.*;!com.jogamp.common.jvm.*;!com.jogamp.common.nio.*;!com.jogamp.common.os.*;!com.jogamp.common.type.*;!com.jogamp.common.util.*;!com.jogamp.common.util.awt.*;!com.jogamp.common.util.cache.*;!com.jogamp.common.util.locks.*;!com.jogamp.gluegen.runtime.*;!com.jogamp.openal.*;!com.lowagie.toolbox.*;!com.sun.image.codec.jpeg.*;!com.sun.imageio.plugins.common.*;!com.sun.imageio.spi.*;!com.sun.javadoc.*;!com.sun.jdi.*;!com.sun.jdi.connect.*;!com.sun.jdi.event.*;!com.sun.jdi.request.*;!com.sun.jdmk.comm.*;!com.sun.jimi.core.*;!com.sun.media.sound.*;!com.sun.medialib.codec.g3fax.*;!com.sun.medialib.codec.g4fax.*;!com.sun.medialib.codec.jiio.*;!com.sun.medialib.codec.jp2k.*;!com.sun.medialib.codec.jpeg.*;!com.sun.medialib.codec.png.*;!com.sun.medialib.mlib.*;!com.sun.mirror.apt.*;!com.sun.mirror.declaration.*;!com.sun.mirror.type.*;!com.sun.mirror.util.*;!com.sun.msv.datatype.*;!com.sun.msv.datatype.xsd.*;!com.sun.net.ssl.*;!com.sun.net.ssl.internal.ssl.*;!com.sun.opengl.cg.*;!com.sun.opengl.util.*;!com.sun.org.apache.xml.internal.serialize.*;!com.sun.tools.apt.*;!com.sun.tools.javac.*;!com.sun.tools.javadoc.*;!com.sun.tools.javah.*;!com.sun.tools.javah.oldjavah.*;!com.sun.xml.fastinfoset.sax.*;!com.sun.xml.fastinfoset.stax.*;!com.sun.xml.stream.writers.*;!com.thoughtworks.qdox.*;!com.toedter.calendar.*;!com.touchgraph.graphlayout.*;!com.touchgraph.graphlayout.interaction.*;!com.twelvemonkeys.imageio.*;!com.twelvemonkeys.imageio.color.*;!com.twelvemonkeys.imageio.spi.*;!com.twelvemonkeys.imageio.stream.*;!com.twelvemonkeys.imageio.util.*;!com.twelvemonkeys.lang.*;!com.twelvemonkeys.util.*;!com.twelvemonkeys.util.regex.*;!gnu.gcj.*;!groovyjarjarasm.asm.tree.analysis.*;!javax.accessibility.*;!javax.annotation.*;!javax.annotation.processing.*;!javax.crypto.*;!javax.crypto.interfaces.*;!javax.crypto.spec.*;!javax.ejb.*;!javax.imageio.*;!javax.imageio.event.*;!javax.imageio.metadata.*;!javax.imageio.plugins.jpeg.*;!javax.imageio.spi.*;!javax.imageio.stream.*;!javax.inject.*;!javax.jdo.*;!javax.jdo.spi.*;!javax.jmdns.*;!javax.jms.*;!javax.lang.model.element.*;!javax.mail.*;!javax.mail.internet.*;!javax.management.*;!javax.management.modelmbean.*;!javax.management.remote.*;!javax.management.remote.rmi.*;!javax.management.timer.*;!javax.naming.*;!javax.naming.directory.*;!javax.naming.event.*;!javax.naming.spi.*;!javax.net.*;!javax.net.ssl.*;!javax.print.*;!javax.print.attribute.*;!javax.print.attribute.standard.*;!javax.rmi.*;!javax.rmi.ssl.*;!javax.script.*;!javax.security.auth.*;!javax.security.auth.callback.*;!javax.security.auth.login.*;!javax.security.auth.x500.*;!javax.security.cert.*;!javax.security.jacc.*;!javax.servlet.jsp.*;!javax.sound.sampled.*;!javax.sql.*;!javax.sql.rowset.*;!javax.swing.*;!javax.swing.border.*;!javax.swing.colorchooser.*;!javax.swing.event.*;!javax.swing.filechooser.*;!javax.swing.plaf.*;!javax.swing.plaf.basic.*;!javax.swing.plaf.metal.*;!javax.swing.plaf.synth.*;!javax.swing.table.*;!javax.swing.text.*;!javax.swing.text.html.*;!javax.swing.text.html.parser.*;!javax.swing.text.rtf.*;!javax.swing.tree.*;!javax.swing.undo.*;!javax.tools.*;!javax.validation.*;!javax.validation.constraints.*;!javax.validation.groups.*;!javax.validation.metadata.*;!javax.wsdl.*;!javax.wsdl.extensions.*;!javax.wsdl.extensions.http.*;!javax.wsdl.extensions.mime.*;!javax.wsdl.extensions.soap.*;!javax.wsdl.factory.*;!javax.wsdl.xml.*;!javax.xml.rpc.*;!javax.xml.rpc.encoding.*;!javax.xml.rpc.handler.*;!javax.xml.rpc.handler.soap.*;!javax.xml.rpc.holders.*;!javax.xml.rpc.server.*;!javax.xml.rpc.soap.*;!javax.xml.soap.*;!javax.xml.transform.stax.*;!jline.*;!jogamp.common.os.*;!jogamp.common.util.locks.*;!jrockit.vm.*;!jxl.*;!jxl.biff.*;!jxl.format.*;!jxl.read.biff.*;!jxl.write.*;!jxl.write.biff.*;!kaffe.util.*;!magick.*;!mondrian.olap.*;!mondrian.spi.*;!net.geom.j3d.objects.*;!net.jcip.annotations.*;!net.sf.cglib.beans.*;!net.sf.cglib.core.*;!net.sf.cglib.proxy.*;!net.sf.cglib.reflect.*;!net.sf.cglib.transform.*;!net.sf.cglib.transform.impl.*;!net.sf.saxon.xpath.*;!net.sourceforge.barbecue.*;!net.sourceforge.barbecue.linear.code39.*;!net.sourceforge.barbecue.linear.ean.*;!net.sourceforge.barbecue.output.*;!netscape.ldap.*;!nu.xom.*;!oracle.xml.parser.*;!oracle.xml.parser.v2.*;!org.apache.bsf.*;!org.apache.bsf.util.*;!org.apache.commons.cli.*;!org.apache.commons.discovery.*;!org.apache.commons.discovery.resource.*;!org.apache.commons.discovery.resource.classes.*;!org.apache.commons.discovery.resource.names.*;!org.apache.commons.discovery.tools.*;!org.apache.commons.javaflow.*;!org.apache.commons.net.ftp.*;!org.apache.commons.net.ftp.parser.*;!org.apache.commons.net.pop3.*;!org.apache.commons.net.smtp.*;!org.apache.crimson.jaxp.*;!org.apache.env.*;!org.apache.harmony.luni.util.*;!org.apache.ivy.*;!org.apache.ivy.core.cache.*;!org.apache.ivy.core.event.*;!org.apache.ivy.core.event.download.*;!org.apache.ivy.core.event.resolve.*;!org.apache.ivy.core.module.descriptor.*;!org.apache.ivy.core.module.id.*;!org.apache.ivy.core.report.*;!org.apache.ivy.core.resolve.*;!org.apache.ivy.core.settings.*;!org.apache.ivy.plugins.matcher.*;!org.apache.ivy.plugins.resolver.*;!org.apache.ivy.util.*;!org.apache.jackrabbit.webdav.*;!org.apache.jackrabbit.webdav.client.methods.*;!org.apache.jackrabbit.webdav.property.*;!org.apache.jackrabbit.webdav.version.*;!org.apache.jackrabbit.webdav.xml.*;!org.apache.log.*;!org.apache.oro.text.regex.*;!org.apache.poi.hdgf.extractor.*;!org.apache.poi.hpbf.extractor.*;!org.apache.poi.hslf.extractor.*;!org.apache.poi.hsmf.*;!org.apache.poi.hsmf.datatypes.*;!org.apache.poi.hsmf.extractor.*;!org.apache.poi.hssf.record.formula.*;!org.apache.poi.hssf.record.formula.eval.*;!org.apache.poi.hssf.record.formula.udf.*;!org.apache.poi.hwpf.*;!org.apache.poi.hwpf.extractor.*;!org.apache.poi.sl.usermodel.*;!org.apache.torque.task.*;!org.apache.velocity.*;!org.apache.velocity.app.*;!org.apache.velocity.context.*;!org.apache.velocity.servlet.*;!org.apache.xerces.utils.regex.*;!org.apache.xml.resolver.*;!org.apache.xml.resolver.readers.*;!org.apache.xml.resolver.tools.*;!org.apache.xmlbeans.impl.xpath.saxon.*;!org.apache.xmlbeans.impl.xquery.saxon.*;!org.apache.xmlrpc.*;!org.codehaus.jettison.*;!org.codehaus.jettison.mapped.*;!org.codehaus.plexus.interpolation.*;!org.codehaus.plexus.interpolation.os.*;!org.codehaus.plexus.interpolation.reflection.*;!org.codehaus.plexus.interpolation.util.*;!org.daml.kazuki.*;!org.eclipse.core.internal.runtime.auth.*;!org.eclipse.core.resources.*;!org.eclipse.emf.validation.internal.*;!org.eclipse.emf.validation.model.*;!org.eclipse.emf.validation.service.*;!org.eclipse.jface.text.*;!org.eclipse.jface.text.rules.*;!org.eclipse.swt.graphics.*;!org.eclipse.swt.layout.*;!org.eclipse.swt.widgets.*;!org.eclipse.text.edits.*;!org.eclipse.ui.*;!org.freehep.graphics2d.*;!org.freehep.util.export.*;!org.fusesource.jansi.*;!org.gjt.xpp.*;!org.ietf.jgss.*;!org.jaxen.*;!org.jaxen.dom.*;!org.jaxen.dom4j.*;!org.jaxen.jdom.*;!org.jaxen.pattern.*;!org.jaxen.saxpath.*;!org.joda.convert.*;!org.jvnet.fastinfoset.*;!org.jvnet.staxex.*;!org.krysalis.barcode4j.*;!org.krysalis.barcode4j.impl.*;!org.krysalis.barcode4j.impl.codabar.*;!org.krysalis.barcode4j.impl.code128.*;!org.krysalis.barcode4j.impl.code39.*;!org.krysalis.barcode4j.impl.datamatrix.*;!org.krysalis.barcode4j.impl.fourstate.*;!org.krysalis.barcode4j.impl.int2of5.*;!org.krysalis.barcode4j.impl.pdf417.*;!org.krysalis.barcode4j.impl.postnet.*;!org.krysalis.barcode4j.impl.upcean.*;!org.krysalis.barcode4j.output.*;!org.krysalis.barcode4j.output.bitmap.*;!org.krysalis.barcode4j.output.svg.*;!org.krysalis.barcode4j.tools.*;!org.mockito.asm.signature.*;!org.objectweb.asm.tree.*;!org.objectweb.asm.tree.analysis.*;!org.objectweb.asm.util.*;!org.objectweb.jonas_tm.*;!org.objectweb.jotm.*;!org.omg.CORBA.*;!org.omg.CosNaming.*;!org.openxmlformats.schemas.officeDocument.x2006.math.*;!org.openxmlformats.schemas.schemaLibrary.x2006.main.*;!org.springframework.beans.factory.*;!org.springframework.beans.factory.xml.*;!org.springframework.core.io.*;!org.testng.*;!org.testng.annotations.*;!schemasMicrosoftComOfficePowerpoint.*;!schemasMicrosoftComOfficeWord.*;!sun.applet.*;!sun.awt.*;!sun.awt.image.*;!sun.awt.image.codec.*;!sun.io.*;!sun.java2d.pipe.*;!sun.misc.*;!sun.nio.cs.*;!sun.reflect.*;!sun.rmi.rmic.*;!sun.rmi.server.*;!sun.security.action.*;!sun.security.provider.*;!sun.tools.javac.*;!sun.tools.native2ascii.*;!weblogic.*;!weblogic.apache.xml.serialize.*,*")

OsgiKeys.privatePackage := Seq("!scala.*,!java.*,"  +
"!COM.jrockit.reflect.*;!COM.newmonics.PercClassLoader.*;!bitronix.tm.*;!bsh.*;!com.apple.eawt.*;!com.apple.eio.*;!com.apple.mrj.*;!com.catalysoft.swing.unicode.*;!com.coyotegulch.jisp.*;!com.enterprisedt.net.ftp.*;!com.hexidec.ekit.*;!com.hp.hpl.jena.ontology.tidy.*;!com.hp.hpl.jena.query.*;!com.hp.hpl.jena.reasoner.dig.*;!com.ibm.ejs.jts.jta.*;!com.ibm.uvm.tools.*;!com.ibm.websphere.jtaextensions.*;!com.ibm.ws.Transaction.*;!com.ibm.wsdl.extensions.soap.*;!com.jcraft.jsch.*;!com.jgoodies.looks.plastic.*;!com.jgoodies.looks.plastic.theme.*;!com.jogamp.common.*;!com.jogamp.common.jvm.*;!com.jogamp.common.nio.*;!com.jogamp.common.os.*;!com.jogamp.common.type.*;!com.jogamp.common.util.*;!com.jogamp.common.util.awt.*;!com.jogamp.common.util.cache.*;!com.jogamp.common.util.locks.*;!com.jogamp.gluegen.runtime.*;!com.jogamp.openal.*;!com.lowagie.toolbox.*;!com.sun.image.codec.jpeg.*;!com.sun.imageio.plugins.common.*;!com.sun.imageio.spi.*;!com.sun.javadoc.*;!com.sun.jdi.*;!com.sun.jdi.connect.*;!com.sun.jdi.event.*;!com.sun.jdi.request.*;!com.sun.jdmk.comm.*;!com.sun.jimi.core.*;!com.sun.media.sound.*;!com.sun.medialib.codec.g3fax.*;!com.sun.medialib.codec.g4fax.*;!com.sun.medialib.codec.jiio.*;!com.sun.medialib.codec.jp2k.*;!com.sun.medialib.codec.jpeg.*;!com.sun.medialib.codec.png.*;!com.sun.medialib.mlib.*;!com.sun.mirror.apt.*;!com.sun.mirror.declaration.*;!com.sun.mirror.type.*;!com.sun.mirror.util.*;!com.sun.msv.datatype.*;!com.sun.msv.datatype.xsd.*;!com.sun.net.ssl.*;!com.sun.net.ssl.internal.ssl.*;!com.sun.opengl.cg.*;!com.sun.opengl.util.*;!com.sun.org.apache.xml.internal.serialize.*;!com.sun.tools.apt.*;!com.sun.tools.javac.*;!com.sun.tools.javadoc.*;!com.sun.tools.javah.*;!com.sun.tools.javah.oldjavah.*;!com.sun.xml.fastinfoset.sax.*;!com.sun.xml.fastinfoset.stax.*;!com.sun.xml.stream.writers.*;!com.thoughtworks.qdox.*;!com.toedter.calendar.*;!com.touchgraph.graphlayout.*;!com.touchgraph.graphlayout.interaction.*;!com.twelvemonkeys.imageio.*;!com.twelvemonkeys.imageio.color.*;!com.twelvemonkeys.imageio.spi.*;!com.twelvemonkeys.imageio.stream.*;!com.twelvemonkeys.imageio.util.*;!com.twelvemonkeys.lang.*;!com.twelvemonkeys.util.*;!com.twelvemonkeys.util.regex.*;!gnu.gcj.*;!groovyjarjarasm.asm.tree.analysis.*;!javax.accessibility.*;!javax.annotation.*;!javax.annotation.processing.*;!javax.crypto.*;!javax.crypto.interfaces.*;!javax.crypto.spec.*;!javax.ejb.*;!javax.imageio.*;!javax.imageio.event.*;!javax.imageio.metadata.*;!javax.imageio.plugins.jpeg.*;!javax.imageio.spi.*;!javax.imageio.stream.*;!javax.inject.*;!javax.jdo.*;!javax.jdo.spi.*;!javax.jmdns.*;!javax.jms.*;!javax.lang.model.element.*;!javax.mail.*;!javax.mail.internet.*;!javax.management.*;!javax.management.modelmbean.*;!javax.management.remote.*;!javax.management.remote.rmi.*;!javax.management.timer.*;!javax.naming.*;!javax.naming.directory.*;!javax.naming.event.*;!javax.naming.spi.*;!javax.net.*;!javax.net.ssl.*;!javax.print.*;!javax.print.attribute.*;!javax.print.attribute.standard.*;!javax.rmi.*;!javax.rmi.ssl.*;!javax.script.*;!javax.security.auth.*;!javax.security.auth.callback.*;!javax.security.auth.login.*;!javax.security.auth.x500.*;!javax.security.cert.*;!javax.security.jacc.*;!javax.servlet.jsp.*;!javax.sound.sampled.*;!javax.sql.*;!javax.sql.rowset.*;!javax.swing.*;!javax.swing.border.*;!javax.swing.colorchooser.*;!javax.swing.event.*;!javax.swing.filechooser.*;!javax.swing.plaf.*;!javax.swing.plaf.basic.*;!javax.swing.plaf.metal.*;!javax.swing.plaf.synth.*;!javax.swing.table.*;!javax.swing.text.*;!javax.swing.text.html.*;!javax.swing.text.html.parser.*;!javax.swing.text.rtf.*;!javax.swing.tree.*;!javax.swing.undo.*;!javax.tools.*;!javax.validation.*;!javax.validation.constraints.*;!javax.validation.groups.*;!javax.validation.metadata.*;!javax.wsdl.*;!javax.wsdl.extensions.*;!javax.wsdl.extensions.http.*;!javax.wsdl.extensions.mime.*;!javax.wsdl.extensions.soap.*;!javax.wsdl.factory.*;!javax.wsdl.xml.*;!javax.xml.rpc.*;!javax.xml.rpc.encoding.*;!javax.xml.rpc.handler.*;!javax.xml.rpc.handler.soap.*;!javax.xml.rpc.holders.*;!javax.xml.rpc.server.*;!javax.xml.rpc.soap.*;!javax.xml.soap.*;!javax.xml.transform.stax.*;!jline.*;!jogamp.common.os.*;!jogamp.common.util.locks.*;!jrockit.vm.*;!jxl.*;!jxl.biff.*;!jxl.format.*;!jxl.read.biff.*;!jxl.write.*;!jxl.write.biff.*;!kaffe.util.*;!magick.*;!mondrian.olap.*;!mondrian.spi.*;!net.geom.j3d.objects.*;!net.jcip.annotations.*;!net.sf.cglib.beans.*;!net.sf.cglib.core.*;!net.sf.cglib.proxy.*;!net.sf.cglib.reflect.*;!net.sf.cglib.transform.*;!net.sf.cglib.transform.impl.*;!net.sf.saxon.xpath.*;!net.sourceforge.barbecue.*;!net.sourceforge.barbecue.linear.code39.*;!net.sourceforge.barbecue.linear.ean.*;!net.sourceforge.barbecue.output.*;!netscape.ldap.*;!nu.xom.*;!oracle.xml.parser.*;!oracle.xml.parser.v2.*;!org.apache.bsf.*;!org.apache.bsf.util.*;!org.apache.commons.cli.*;!org.apache.commons.discovery.*;!org.apache.commons.discovery.resource.*;!org.apache.commons.discovery.resource.classes.*;!org.apache.commons.discovery.resource.names.*;!org.apache.commons.discovery.tools.*;!org.apache.commons.javaflow.*;!org.apache.commons.net.ftp.*;!org.apache.commons.net.ftp.parser.*;!org.apache.commons.net.pop3.*;!org.apache.commons.net.smtp.*;!org.apache.crimson.jaxp.*;!org.apache.env.*;!org.apache.harmony.luni.util.*;!org.apache.ivy.*;!org.apache.ivy.core.cache.*;!org.apache.ivy.core.event.*;!org.apache.ivy.core.event.download.*;!org.apache.ivy.core.event.resolve.*;!org.apache.ivy.core.module.descriptor.*;!org.apache.ivy.core.module.id.*;!org.apache.ivy.core.report.*;!org.apache.ivy.core.resolve.*;!org.apache.ivy.core.settings.*;!org.apache.ivy.plugins.matcher.*;!org.apache.ivy.plugins.resolver.*;!org.apache.ivy.util.*;!org.apache.jackrabbit.webdav.*;!org.apache.jackrabbit.webdav.client.methods.*;!org.apache.jackrabbit.webdav.property.*;!org.apache.jackrabbit.webdav.version.*;!org.apache.jackrabbit.webdav.xml.*;!org.apache.log.*;!org.apache.oro.text.regex.*;!org.apache.poi.hdgf.extractor.*;!org.apache.poi.hpbf.extractor.*;!org.apache.poi.hslf.extractor.*;!org.apache.poi.hsmf.*;!org.apache.poi.hsmf.datatypes.*;!org.apache.poi.hsmf.extractor.*;!org.apache.poi.hssf.record.formula.*;!org.apache.poi.hssf.record.formula.eval.*;!org.apache.poi.hssf.record.formula.udf.*;!org.apache.poi.hwpf.*;!org.apache.poi.hwpf.extractor.*;!org.apache.poi.sl.usermodel.*;!org.apache.torque.task.*;!org.apache.velocity.*;!org.apache.velocity.app.*;!org.apache.velocity.context.*;!org.apache.velocity.servlet.*;!org.apache.xerces.utils.regex.*;!org.apache.xml.resolver.*;!org.apache.xml.resolver.readers.*;!org.apache.xml.resolver.tools.*;!org.apache.xmlbeans.impl.xpath.saxon.*;!org.apache.xmlbeans.impl.xquery.saxon.*;!org.apache.xmlrpc.*;!org.codehaus.jettison.*;!org.codehaus.jettison.mapped.*;!org.codehaus.plexus.interpolation.*;!org.codehaus.plexus.interpolation.os.*;!org.codehaus.plexus.interpolation.reflection.*;!org.codehaus.plexus.interpolation.util.*;!org.daml.kazuki.*;!org.eclipse.core.internal.runtime.auth.*;!org.eclipse.core.resources.*;!org.eclipse.emf.validation.internal.*;!org.eclipse.emf.validation.model.*;!org.eclipse.emf.validation.service.*;!org.eclipse.jface.text.*;!org.eclipse.jface.text.rules.*;!org.eclipse.swt.graphics.*;!org.eclipse.swt.layout.*;!org.eclipse.swt.widgets.*;!org.eclipse.text.edits.*;!org.eclipse.ui.*;!org.freehep.graphics2d.*;!org.freehep.util.export.*;!org.fusesource.jansi.*;!org.gjt.xpp.*;!org.ietf.jgss.*;!org.jaxen.*;!org.jaxen.dom.*;!org.jaxen.dom4j.*;!org.jaxen.jdom.*;!org.jaxen.pattern.*;!org.jaxen.saxpath.*;!org.joda.convert.*;!org.jvnet.fastinfoset.*;!org.jvnet.staxex.*;!org.krysalis.barcode4j.*;!org.krysalis.barcode4j.impl.*;!org.krysalis.barcode4j.impl.codabar.*;!org.krysalis.barcode4j.impl.code128.*;!org.krysalis.barcode4j.impl.code39.*;!org.krysalis.barcode4j.impl.datamatrix.*;!org.krysalis.barcode4j.impl.fourstate.*;!org.krysalis.barcode4j.impl.int2of5.*;!org.krysalis.barcode4j.impl.pdf417.*;!org.krysalis.barcode4j.impl.postnet.*;!org.krysalis.barcode4j.impl.upcean.*;!org.krysalis.barcode4j.output.*;!org.krysalis.barcode4j.output.bitmap.*;!org.krysalis.barcode4j.output.svg.*;!org.krysalis.barcode4j.tools.*;!org.mockito.asm.signature.*;!org.objectweb.asm.tree.*;!org.objectweb.asm.tree.analysis.*;!org.objectweb.asm.util.*;!org.objectweb.jonas_tm.*;!org.objectweb.jotm.*;!org.omg.CORBA.*;!org.omg.CosNaming.*;!org.openxmlformats.schemas.officeDocument.x2006.math.*;!org.openxmlformats.schemas.schemaLibrary.x2006.main.*;!org.springframework.beans.factory.*;!org.springframework.beans.factory.xml.*;!org.springframework.core.io.*;!org.testng.*;!org.testng.annotations.*;!schemasMicrosoftComOfficePowerpoint.*;!schemasMicrosoftComOfficeWord.*;!sun.applet.*;!sun.awt.*;!sun.awt.image.*;!sun.awt.image.codec.*;!sun.io.*;!sun.java2d.pipe.*;!sun.misc.*;!sun.nio.cs.*;!sun.reflect.*;!sun.rmi.rmic.*;!sun.rmi.server.*;!sun.security.action.*;!sun.security.provider.*;!sun.tools.javac.*;!sun.tools.native2ascii.*;!weblogic.*;!weblogic.apache.xml.serialize.*,*")


scalariformSettings


resolvers += "IDB" at "http://igetdb.sourceforge.net/maven2-repository/"

//resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"
resolvers += Resolver.mavenLocal

resolvers += "IGN snapshots" at "https://forge-cogit.ign.fr/nexus/content/repositories/snapshots"
resolvers += "IGN releases" at "https://forge-cogit.ign.fr/nexus/content/repositories/releases"

//resolvers += "ISC-PIF Public" at "http://maven.iscpif.fr/public/"

//resolvers += "ISC-PIF Snapshots" at "http://maven.iscpif.fr/ign-snapshots/"

//resolvers += "ISC-PIF Release" at "http://maven.iscpif.fr/ign-releases/"

resolvers += "ImageJ" at "http://maven.imagej.net/content/repositories/public"

//resolvers += "Central" at "http://repo1.maven.org/maven2/"

resolvers += "Boundless" at "http://repo.boundlessgeo.com/main"

resolvers += "osgeo" at "http://download.osgeo.org/webdav/geotools/"

resolvers += "geosolutions" at "http://maven.geo-solutions.it/"

resolvers += "Hibernate" at "http://www.hibernatespatial.org/repository"

//val openMOLEVersion = "5.0-SNAPSHOT"

//libraryDependencies += "org.openmole" %% "org-openmole-core-dsl" % openMOLEVersion

//libraryDependencies += "org.openmole" %% "org-openmole-plugin-task-scala" % openMOLEVersion

val simplu3DVersion = "1.0-SNAPSHOT"

libraryDependencies += "fr.ign.cogit" % "simplu3d" % simplu3DVersion excludeAll(
    ExclusionRule(organization = "uk.ac.ed.ph.snuggletex"),
    ExclusionRule(organization = "vigna.dsi.unimi.it"),
    ExclusionRule(organization = "net.billylieurance.azuresearch"),
    ExclusionRule(organization = "net.sf.jafama"),
    ExclusionRule(organization = "net.sourceforge.jmatio"),
    ExclusionRule(organization = "jgrapht"),
    ExclusionRule(organization = "org.bethecoder"),
    ExclusionRule(organization = "com.aetrion.flickr"),
    ExclusionRule(organization = "com.caffeineowl.graphics"),
    ExclusionRule(organization = "de.bwaldvogel")
  )
