package controller;

import model.Filiere;
import service.ClasseService;
import service.FiliereService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Classe", urlPatterns = "/classe")
public class Classe extends HttpServlet {
    ClasseService classeService;
    FiliereService filiereService;
    @Override
    public void init() throws ServletException {
        super.init();
        classeService = new ClasseService();
        filiereService = new FiliereService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action){
                case "add":
                    String code = request.getParameter("code");
                    String libelle = request.getParameter("libelle");
                    int frais_inscription = Integer.parseInt(request.getParameter("frais_inscription"));
                    int autres_frais = Integer.parseInt(request.getParameter("autres_frais"));
                    int mensualite = Integer.parseInt(request.getParameter("mensualite"));
                    int filiereId = Integer.parseInt(request.getParameter("idfiliere"));
                    model.Classe classe = new model.Classe(0, libelle,code,frais_inscription,mensualite,autres_frais);
                    Filiere f = new Filiere(filiereId, null);
                    try {
                        classe.setFiliere(f);
                        classeService.addClasse(classe);
                        response.sendRedirect("classe?action=list");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action){
                case "list":
                    try {
                        List<model.Classe> classes = classeService.findAllClasses();
                        request.setAttribute("data", classes);
                        request.getRequestDispatcher("/WEB-INF/classejstl.jsp").forward(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                break;

                case "add":
                    try {
                        List<model.Filiere> filieres = filiereService.allFiliere();
                        request.setAttribute("data", filieres);
                        request.getRequestDispatcher("/WEB-INF/addClasse.jsp").forward(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                break;
                default:
                    request.getRequestDispatcher("/WEB-INF/page404.jsp").forward(request, response);
            }
        }
        else{
            request.getRequestDispatcher("/WEB-INF/page404.jsp").forward(request, response);
        }


    }
}
