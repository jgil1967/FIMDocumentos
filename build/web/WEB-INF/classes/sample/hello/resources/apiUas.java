/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.hello.resources;

import com.uas.dbutil.getTomcatDataSource;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.uas.areaRelationships.areaRelationshipsDTO;
import com.uas.areaRelationships.areaRelationshipsFacade;
import com.uas.areas.areaDTO;
import com.uas.areas.areaFacade;
import com.uas.dates.DatesDTO;
import com.uas.dates.DatesFacade;
import com.uas.dates.filters.filtersDTO.FiltersDTO;
import com.uas.document.DocumentDTO;
import com.uas.document.DocumentFacade;
import com.uas.documentKeywordRelationship.documentKeywordRelationshipDTO;
import com.uas.documentKeywordRelationship.documentKeywordRelationshipFacade;
import com.uas.keyword.KeywordDTO;
import com.uas.keyword.KeywordFacade;
import com.uas.object.ObjectDTO;
import com.uas.object.ObjectFacade;
import com.uas.usuarios.UsuarioDTO;
import com.uas.usuarios.UsuarioFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

/**
 *
 * @author jonathangil
 */
@Path("/hello")
public class apiUas {
            DocumentFacade dFac = null;
    ObjectFacade oFac = null;
    
    DatesFacade datFac= null;
   UsuarioFacade uFac  = null;
    KeywordFacade kFac = null;
    areaFacade aFac = null;
    documentKeywordRelationshipFacade documentKeywordRelationshipFacade = null;
    areaRelationshipsFacade arFac = null;
    
    
          @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
    @Path("/getPossibleAreasByArea") 
      public ArrayList<areaDTO> getPossibleAreasByArea(areaDTO oDto){
          aFac = new areaFacade();
          return aFac.getPossibleAreasByArea(oDto);
      }
            @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAreasByArea")      
      public ArrayList<areaDTO> getAreasByArea(areaDTO oDto){
          aFac = new areaFacade();
          return aFac.getAreasByArea(oDto);
      }
        @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAreasByArea2") 
    public ArrayList<areaDTO> getAreasByArea2(areaDTO oDto) {
              aFac = new areaFacade();
          return aFac.getAreasByArea2(oDto);
    }
      @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
    @Path("/createAreaRelationship")   
     public areaRelationshipsDTO createAreaRelationship (areaRelationshipsDTO dto){
         arFac = new areaRelationshipsFacade();
         return arFac.createAreaRelationship(dto);
     }
       @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteAreaRelationship")   
    public areaRelationshipsDTO deleteAreaRelationship (areaRelationshipsDTO dto){
        arFac = new areaRelationshipsFacade();
        return arFac.deleteAreaRelationship(dto);
    }
    
    //es por la librería de JSON
                  @GET
		 @Produces(MediaType.APPLICATION_JSON)
                 @Path("/getDocuments")
		public ArrayList<DocumentDTO> getDocuments() 
                {
            dFac = new DocumentFacade();
             return dFac.getDocuments();
                }
                 @POST
		 @Produces(MediaType.APPLICATION_JSON)
                 @Path("/getDocumentsOnlyEnabled")
		public ArrayList<DocumentDTO> getDocumentsOnlyEnabled(ArrayList<areaDTO> areas) 
                {
            dFac = new DocumentFacade();
             return dFac.getDocumentsOnlyEnabled(areas);
                }
                   @GET
		 @Produces(MediaType.APPLICATION_JSON)
                 @Path("/getUsuarios")
		public ArrayList<UsuarioDTO> getUsuarios() 
                {
            uFac = new UsuarioFacade();
             return uFac.obtenerUsuariosForRoot();
                }
                 @GET
		 @Produces(MediaType.APPLICATION_JSON)
                 @Path("/getUsuariosForAdministrator")
		public ArrayList<UsuarioDTO> getUsuariosForAdministrator() 
                {
            uFac = new UsuarioFacade();
             return uFac.obtenerUsuariosForAdministrator();
                }
                   @GET
		 @Produces(MediaType.APPLICATION_JSON)
                 @Path("/getAreas")
		public ArrayList<areaDTO> getAreas() 
                {
            aFac = new areaFacade();
             return aFac.getAreas();
                }
                  @GET
		 @Produces(MediaType.APPLICATION_JSON)
                 @Path("/getKeywords")
		public ArrayList<KeywordDTO> getKeywords() 
                {
            kFac = new KeywordFacade();
             return kFac.getKeywords();
                }
                
                
                 @GET
		 @Produces(MediaType.APPLICATION_JSON)
                 @Path("/getDatesDTO")
              public  DatesDTO getDatesDTO (){
                    datFac = new DatesFacade();
                    return datFac.getDatesDTO();
                }
                  @GET
		 @Produces(MediaType.APPLICATION_JSON)
                 @Path("/pow")
		public void pow() 
                {
                    
                   double num = Math.pow(0.515367327881, 4);
                   double num2 = 0.515367327881 * 0.515367327881 * 0.515367327881 * 0.515367327881;
                        //System.out.println("num: "+ num);
                                //System.out.println("num2: "+ num2);
                }
    
               @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
    @Path("/getUsuarioByID")  
               public UsuarioDTO getUsuarioByID(UsuarioDTO dto) {
                   uFac = new UsuarioFacade();
                   return uFac.getUsuarioByID(dto);
               }
              @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
    @Path("/createArea")   
              public areaDTO createArea (areaDTO dto){
                   oFac = new ObjectFacade();
       dto.setId(oFac.createObject(dto).getId()); 
       aFac = new areaFacade ();
      return aFac.createArea(dto);
              }
                
              @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
              @Path("/verificaDisponibilidadUsuario")
              public UsuarioDTO verificaDisponibilidadUsuario(UsuarioDTO dto) throws Exception{
                //  //System.out.println("Hello api verificaDisponibilidadUsuario " );
                  uFac = new UsuarioFacade();
                  return uFac.verificaDisponibilidadUsuario(dto);
              }
              
              @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
    @Path("/getDocumentsFilters")
    public ArrayList<DocumentDTO> getDocumentsFilters(FiltersDTO filters) throws Exception
    {
       dFac= new DocumentFacade();
        return dFac.getDocuments(filters);
     
    }
                                @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
    @Path("/createUsuario")
    public UsuarioDTO createUsuario(UsuarioDTO dto) throws Exception
    {
          oFac = new ObjectFacade();
       dto.setId(oFac.createObject(dto).getId()); 
        uFac = new UsuarioFacade();
       return  uFac.createUsuario(dto);
    }
                         @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateUsuario")
    public UsuarioDTO updateUsuario(UsuarioDTO dto) throws Exception
    {
          oFac = new ObjectFacade();
       oFac.updateObject(dto); 
        uFac = new UsuarioFacade();
       return  uFac.updateUsuario(dto);
    }
    
     @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateArea")
    public areaDTO updateArea(areaDTO dto)
    {
        oFac = new ObjectFacade();
       oFac.updateObject(dto); 
             aFac = new areaFacade ();
       return aFac.updateArea(dto);
    }
              @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
    @Path("/createDocument")
    public DocumentDTO createDocument(DocumentDTO dDto) throws Exception
    {
       // //System.out.println("dDto : "+ dDto.toString());
      oFac = new ObjectFacade();
       dDto.setId(oFac.createObject(dDto).getId()); 
       dFac = new DocumentFacade();
       return  dFac.createDocument(dDto);
     //  return null;
    }
    
            @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateDocument")
    public DocumentDTO updateDocument(DocumentDTO dDto) throws Exception
    {
        
       oFac = new ObjectFacade();
       oFac.updateObject(dDto);
       dFac = new DocumentFacade();
       return  dFac.updateDocument(dDto);
    }
          @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
    @Path("/searchDocuments")
    public ArrayList<DocumentDTO> searchDocuments(DocumentDTO dDto) throws Exception
    {
     // //System.out.println("Hello: " + dDto.getQuery());
       dFac = new DocumentFacade();
       return  dFac.searchDocuments(dDto);
    }
            @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
    @Path("/createTag")
    public KeywordDTO createTag(KeywordDTO tag) throws Exception
    {
     //   //System.out.println("tag : "+ tag.toString());
       oFac = new ObjectFacade();
       tag.setId(oFac.createObject(tag).getId()); 
       kFac = new KeywordFacade();
       return  kFac.createKeyword(tag);
    }
          @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateKeyword")
    public KeywordDTO updateKeyword(KeywordDTO tag) throws Exception
    {
     //   //System.out.println("tag : "+ tag.toString());
       oFac = new ObjectFacade();
     oFac.updateObject(tag);
       return  tag;
    }
    //createDocumentKeywordRelationship
          @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
    @Path("/createDocumentKeywordRelationship")
    public documentKeywordRelationshipDTO createDocumentKeywordRelationship(documentKeywordRelationshipDTO dDto) throws Exception
    {
        documentKeywordRelationshipFacade = new documentKeywordRelationshipFacade();
       return  documentKeywordRelationshipFacade.createdocumentKeywordRelationshipDTO(dDto);
    }
           @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteDocumentKeywordRelationshipDTO")
    public documentKeywordRelationshipDTO deleteDocumentKeywordRelationshipDTO(documentKeywordRelationshipDTO dDto) throws Exception
    {
        documentKeywordRelationshipFacade = new documentKeywordRelationshipFacade();
       return  documentKeywordRelationshipFacade.deleteDocumentKeywordRelationshipDTO(dDto);
    }
          @POST
    @Consumes({MediaType.APPLICATION_JSON})
     @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteDocumentKeywordRelationshipsByDocument")
    public DocumentDTO deleteDocumentKeywordRelationshipsByDocument(DocumentDTO document) throws Exception
    {
        documentKeywordRelationshipFacade = new documentKeywordRelationshipFacade();
       return  documentKeywordRelationshipFacade.deleteDocumentKeywordRelationshipsByDocument(document);
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() throws SQLException {
        getTomcatDataSource gd = new getTomcatDataSource ();
          Connection c = null;
        try {
            c = gd.getTomcatDataSource().getConnection();
        } catch (NamingException ex) {
        ex.printStackTrace();
        }
        finally{
            try {
                c.close();
            } catch (SQLException ex) {
            ex.printStackTrace();}
        }
        return "Hello Jersey";
    }
    
    //X-Y-Z-SATELITE 7.docx
    @GET
    @Path("/downloadDocument/{filename}")
    public Response downloadDocument(@PathParam("filename") final String filename) throws Exception
    {
        //System.out.println("Filename : "+ filename);
     
       StreamingOutput fileStream =  new StreamingOutput() 
        {
            @Override
            public void write(java.io.OutputStream output) throws IOException, WebApplicationException 
            {
                try
                {
                    java.nio.file.Path path = Paths.get(returnPath()+filename);
                    byte[] data = Files.readAllBytes(path);
                    output.write(data);
                    output.flush();
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        };
        return Response
                .ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition","attachment; filename = "+filename)
                .build();
        
     
    }
    
    

    
    int num=0;
    String nombreFinal = "";
    public String checkIfExistsAndReturnValid (String fileName, String name){
        
              //System.out.println("File existed : " + fileName);
              //System.out.println("File name : " + name);
              nombreFinal = name;
                 File f = new File(fileName);

	  if(f.exists()){
		
                  num++;
                  String extension = "";

int i = name.lastIndexOf('.');
if (i > 0) {
    extension = name.substring(i+1);
    //System.out.println("i : " + i);
    //System.out.println("extension : " + extension);
    String nameASumar = name.substring(0,15);
    //System.out.println("fileDetail.getFileName() a sumar : " + nameASumar);
    String ruta = fileName.substring(0,fileName.lastIndexOf(name));
    //System.out.println("ruta :" + ruta); 
    ruta =ruta + nameASumar+ "("+num+")"+"."+extension;
    //System.out.println("ruta nueva : " + ruta);
    return checkIfExistsAndReturnValid (ruta,nameASumar+ "("+num+")"+"."+extension);
    
}
	  }else{
		  
		        return fileName;    
	
	  }
        return fileName;
    }
    
    
    
    public String returnPath(){
       // //System.out.println(new File(".").getAbsolutePath());
	Properties prop = new Properties();
	InputStream input = null;

	try {

		input = new FileInputStream("config.properties");

		// load a properties file
		prop.load(input);

		// get the property value and print it out
		return prop.getProperty("pathForFiles");

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
        return null;
    }
    
        @POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
		@FormDataParam("file") InputStream uploadedInputStream,
		@FormDataParam("file") FormDataContentDisposition fileDetail) {
        num=0;
        String output = "";
		//String uploadedFileLocation = "/Users/jonathangil/apache-tomcat-8.0.37/FIMFiles/" + fileDetail.getFileName();
               String uploadedFileLocation = returnPath() + fileDetail.getFileName();
              String rutaAGuardar =  checkIfExistsAndReturnValid(uploadedFileLocation,fileDetail.getFileName());
          ////////
                writeToFile(uploadedInputStream, rutaAGuardar);
		 output = rutaAGuardar;
	return Response.status(200).entity(nombreFinal).build();

	}
                
	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,
		String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
