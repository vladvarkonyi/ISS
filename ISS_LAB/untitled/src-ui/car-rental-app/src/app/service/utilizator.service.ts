import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UtilizatorService {
  private apiUrl = 'http://localhost:8080/api/utilizatori';

  private utilizatorCurent = new BehaviorSubject<any>(this.citesteDinStorage());
  utilizator$ = this.utilizatorCurent.asObservable();

  constructor(private http: HttpClient) { }

  login(credentials: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credentials);
  }

  salveazaUtilizator(utilizator: any) {
    localStorage.setItem('user_curent', JSON.stringify(utilizator));
    this.utilizatorCurent.next(utilizator);
  }

  logout() {
    localStorage.removeItem('user_curent');
    this.utilizatorCurent.next(null);
  }

  private citesteDinStorage() {
    if (typeof window !== 'undefined') {
      const date = localStorage.getItem('user_curent');
      return date ? JSON.parse(date) : null;
    }
    return null;
  }

  getUtilizatori(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  addUtilizator(utilizator: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, utilizator);
  }

  updateUtilizator(id: number, utilizator: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, utilizator);
  }

  deleteUtilizator(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
