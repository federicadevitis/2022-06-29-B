package it.polito.tdp.itunes.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	private Graph<Album, DefaultWeightedEdge> grafo;
	private ItunesDAO dao;
	List<Album> successori;
	
	
	public Model() {
		dao = new ItunesDAO();
	}
	
	public void creaGrafo(Integer n) {
		grafo = new SimpleDirectedWeightedGraph<Album, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, dao.getAllVertices(n));
		System.out.println(grafo.vertexSet().size());
		
		for(Album a1: dao.getAllVertices(n)) {
			for(Album a2: dao.getAllVertices(n)) {
				int peso = a1.getDurata()+a2.getDurata();
				if(!a1.equals(a2) && peso>4*n) {
					if(grafo.containsVertex(a1) && grafo.containsVertex(a2)) {
						if(grafo.containsEdge(a1, a2) || grafo.containsEdge(a2,a1)) {
							continue;
						}
						if(a1.getDurata()>a2.getDurata()) {
							Graphs.addEdge(grafo, a2, a1, peso);
						}
						else if(a1.getDurata()<a2.getDurata()) {
							Graphs.addEdge(grafo, a1, a2, peso);
						}
					}
				}
			}
		}
		System.out.println(grafo.edgeSet().size());
	}
	
	
	public int nVertici() {
		return grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return grafo.edgeSet().size();
	}
	
	
	public void calcolaBilancio() {

		
		for(Album album : grafo.vertexSet()) {
			double sumEntranti =0;
			double sumUscenti=0;

			for(DefaultWeightedEdge e : grafo.outgoingEdgesOf(album)) {
				sumUscenti = sumUscenti + grafo.getEdgeWeight(e);
			}
			
			for(DefaultWeightedEdge e : grafo.incomingEdgesOf(album)) {
				sumEntranti = sumEntranti + grafo.getEdgeWeight(e);
			}
			

			int bilancio = (int) (sumEntranti-sumUscenti);
			album.setBilancio(bilancio);
		}
		
	}
	
	
	
	public List<Album> listaSuccessori(Album a){
		this.calcolaBilancio();
		successori = new LinkedList<>(Graphs.successorListOf(this.grafo, a)); 
		Collections.sort(successori);
		return successori;
	}

	public List <Album> getVerticiGrafoOrdinati() {
		List <Album> lista = new LinkedList<>(grafo.vertexSet());
		Collections.sort(lista, new ComparatoreAlbumAlfab());
		return lista;
	}
}
